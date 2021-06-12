import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

public class Jogo implements Serializable {
    private static final long serialVersionUID = 7389363689072708873L;
    private EquipaJogo casa;
    private EquipaJogo fora;
    private int golosCasa;
    private int golosFora;
    private LocalDate date;
    private boolean realizado;

    //              Constructors                //

    public Jogo(){
        this.casa = new EquipaJogo();
        this.fora = new EquipaJogo();
        this.golosCasa = 0;
        this.golosFora = 0;
        this.date = LocalDate.now();
        this.realizado = false;
    }

    public Jogo (EquipaJogo casa,EquipaJogo fora){
        golosCasa = 0;
        golosFora = 0;
        this.casa = casa;
        this.fora = fora;
        date = LocalDate.now();
        realizado = false;
    }

    public Jogo (EquipaJogo casa, EquipaJogo fora, int golosCasa, int golosFora, LocalDate data, boolean realizado){
        this.casa = casa;
        this.fora = fora;
        this.golosCasa = golosCasa;
        this.golosFora = golosFora;
        this.date = data;
        this.realizado = realizado;
    }

    public Jogo(Jogo jogo){
        this.casa = jogo.getCasa();
        this.fora = jogo.getFora();
        this.golosCasa = jogo.getGolosCasa();
        this.golosFora = jogo.getGolosFora();
        this.date = jogo.getDate();
        this.realizado = jogo.isRealizado();
    }

    public boolean isRealizado() {
        return realizado;
    }

    public void setRealizado(boolean realizado) {
        this.realizado = realizado;
    }

    public void goloCasa(){
        setGolosCasa(this.golosCasa + 1);
    }

    public void goloFora(){
        setGolosFora(this.golosFora + 1);
    }

    /*
    public String vencedor(){

    }
     */

    //Jogo:<EquipaCasa>,<EquipaFora>,<ScoreCasa>,<ScoreFora>,<Data>,<JogadoresCasa>,<SubstituicoesCasa>,<JogadoresFora>,<SubstituicoesFora>

    public static Jogo parse(String input,CatalogoEquipas equipas){
        String[] campos = input.split(",");
        Equipa casa = equipas.getEquipa(campos[0]);
        Equipa fora = equipas.getEquipa(campos[1]);
        int golosCasa = Integer.parseInt(campos[2]);
        int golosFora = Integer.parseInt(campos[3]);

        String[] data = campos[4].split("-");
        LocalDate dataJogo = LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));

        EquipaJogo equipaJogoCasa = new EquipaJogo(campos[0],casa.getNumeros());
        EquipaJogo equipaJogoFora = new EquipaJogo(campos[1],fora.getNumeros());

        for (int i = 5; i < 16; i++){
            int id = Integer.parseInt(campos[i]);
            if (casa.containsId(id)) equipaJogoCasa.addTitular(id);
        }
        for (int i = 16; i < 19; i++){
            String[] sub = campos[i].split("->");
            int out = Integer.parseInt(sub[0]);
            int in = Integer.parseInt(sub[1]);
            if (equipaJogoCasa.isTitular(out) && equipaJogoCasa.isSuplente(in)) equipaJogoCasa.addSubstituicao(out,in);
        }
        for (int i = 19; i < 30; i++){
            int id = Integer.parseInt(campos[i]);
            if (fora.containsId(id)) equipaJogoFora.addTitular(Integer.parseInt(campos[i]));
        }
        for (int i = 30; i < 33; i++){
            String[] sub = campos[i].split("->");
            int out = Integer.parseInt(sub[0]);
            int in = Integer.parseInt(sub[1]);
            if (equipaJogoCasa.isTitular(out) && equipaJogoCasa.isSuplente(in)) equipaJogoFora.addSubstituicao(out,in);
        }
        return new Jogo(equipaJogoCasa,equipaJogoFora,golosCasa, golosFora, dataJogo,true);
    }

    //              Getters and Setters             //

    public EquipaJogo getCasa() {
        return casa;
    }

    public void setCasa(EquipaJogo casa) {
        this.casa = casa.clone();
    }

    public EquipaJogo getFora() {
        return this.fora;
    }

    public void setFora(EquipaJogo fora) {
        this.fora = fora.clone();
    }

    public int getGolosCasa() {
        return golosCasa;
    }

    public void setGolosCasa(int golosCasa) {
        this.golosCasa = golosCasa;
    }

    public int getGolosFora() {
        return golosFora;
    }

    public void setGolosFora(int golosFora) {
        this.golosFora = golosFora;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }


    public int mediaZona (List<Integer> list, Map <Integer,Integer> overall){
        int total = 0;
        for (int number : list){
            total += overall.get(number);
        }
        return (int) total/list.size();
    }


    public List<Integer> diferencas (Map <Integer,Integer> overallCasa, Map<Integer,Integer> overallFora){
        List<Integer> list = new ArrayList<>();
        //  CASA (DEFESA) - FORA (AVANÇADOS)
        int defesaCasa = mediaZona(casa.getDefesas(),overallCasa);
        int ataqueFora = mediaZona(fora.getAvancados(),overallFora);
        list.add((int) defesaCasa-ataqueFora);
        //  CASA (MEDIOS) - FORA (MEDIOS)
        int mediosCasa = mediaZona(casa.getMedios(),overallCasa);
        int mediosFora = mediaZona(fora.getMedios(),overallFora);
        list.add((int) mediosCasa-mediosFora);
        //  CASA (AVANÇADOS) - FORA (DEFESA)
        int ataqueCasa = mediaZona(casa.getAvancados(),overallCasa);
        int defesaFora = mediaZona(fora.getDefesas(),overallFora);
        list.add((int) ataqueCasa - defesaFora);
        return list;
    }

    public static void wait(int ms)
    {
        try
        {
            Thread.sleep(ms);
        }
        catch(InterruptedException ex)
        {
            Thread.currentThread().interrupt();
        }
    }


    public void calculaJogo (Map <Integer,Integer> overallCasa, Map<Integer,Integer> overallFora){
        List <Integer> list = diferencas(overallCasa,overallFora);
        MomentoJogo mj = new MomentoJogo(list);

        for (int tempo = 0; tempo <= 90; tempo++){
            if (tempo == 45){
                View.clearScreen();
                View.printTitulo("INTERVALO");
                View.printFrase(mj.getGolosCasa() + " - " + mj.getGolosFora());
                if (casa.haSubstituicoes() || fora.haSubstituicoes()){
                    mj.segundaParte();
                    casa.fazeSubstituicoes();
                    fora.fazeSubstituicoes();
                    mj.setDiferencaCasaFora(diferencas(overallCasa,overallFora));
                    View.printFrase("Substituições a serem efetuadas");
                }
                wait(3000);
            }
            View.clearScreen();
            for (int i = 0; i < 3;i++) mj.run();
            View.printFrase("Tempo:" + tempo);
            View.printFrase(mj.getGolosCasa() + " - " + mj.getGolosFora());
            for (int i = 0; i< 3;i++){
                View.printFrase(casa.getSubString(i) + "    " + fora.getSubString(i));
            }
            wait(250);
        }
        this.golosCasa = mj.getGolosCasa();
        this.golosFora = mj.getGolosFora();
        this.realizado = true;
    }

}
