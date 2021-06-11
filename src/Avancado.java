import Atributo.*;

import java.io.Serializable;
import java.util.*;

public class Avancado extends Jogador implements Serializable{
    private static final long serialVersionUID = 3445221744695962821L;
    private int finalizacao;
    private boolean lateral;

    //              Constructors                //

    public Avancado(){
        super();
        this.finalizacao = 0;
        this.lateral = false;
    }

    public Avancado(String name, Integer id, Map<Double, List<Atributo>> atributos,
                    List<String> historico, int finalizacao, boolean lateral)
    {
        super(name, id, atributos, historico);
        this.finalizacao = finalizacao;
        this.lateral = lateral;
    }

    public Avancado(Avancado avancado){
        super(avancado);
        this.finalizacao = avancado.getFinalizacao();
        this.lateral = avancado.isLateral();
    }

    public static Avancado parse(String input, boolean lateral){

        String[] campos = input.split(",");
        String nome = campos[0];
        Integer id = Integer.parseInt(campos[1]);
        Map<Double, List<Atributo>> mapa = new HashMap<>();

        Velocidade velocidade = new Velocidade(Integer.parseInt(campos[2]));
        Jogador.addToMapa(velocidade,0.1,mapa);
        Resistencia resistencia = new Resistencia(Integer.parseInt(campos[3]));
        Jogador.addToMapa(resistencia,0.07,mapa);
        Destreza destreza = new Destreza(Integer.parseInt(campos[4]));
        Impulsao impulso = new Impulsao(Integer.parseInt(campos[5]));
        JogoDeCabeca jogocabeca = new JogoDeCabeca(Integer.parseInt(campos[6]));
        Remate remate = new Remate(Integer.parseInt(campos[7]));
        CapacidadeDePasse capacidadePasse = new CapacidadeDePasse(Integer.parseInt(campos[8]));
        Jogador.addToMapa(capacidadePasse,0.07,mapa);

        int total = 0;
        for (int i = 2; i< 9; i++){
            total += Integer.parseInt(campos[i]);
        }
        double media = (double) total/7;
        int high = (int) media + 10;
        int low = (int) media - 10;

        int finalizacao = Jogador.randomBetween(low, high);
        CapacidadeDefensiva capacidadeDefensiva = new CapacidadeDefensiva(Jogador.randomBetween(low,high));
        Jogador.addToMapa(capacidadeDefensiva,0.01,mapa);
        Motivacao motivacao = new Motivacao(50);
        Jogador.addToMapa(motivacao,0.05,mapa);
        Posicionamento posicionamento = new Posicionamento(Jogador.randomBetween(low,high));
        Jogador.addToMapa(posicionamento,0.05,mapa);

        if (lateral){
            Jogador.addToMapa(remate, 0.15, mapa);
            Jogador.addToMapa(jogocabeca, 0.05, mapa);
            Jogador.addToMapa(impulso, 0.05, mapa);
            Jogador.addToMapa(destreza, 0.1, mapa);
            Cruzamento cruzamento = new Cruzamento(Integer.parseInt(campos[9]));
            Jogador.addToMapa(cruzamento, 0.1, mapa);
        }
        else {
            Jogador.addToMapa(remate, 0.2, mapa);
            Jogador.addToMapa(jogocabeca, 0.1, mapa);
            Jogador.addToMapa(impulso, 0.1, mapa);
            Jogador.addToMapa(destreza, 0.05, mapa);
        }

        List<String> historico = new ArrayList<>();

        return new Avancado(nome, id, mapa, historico, finalizacao, lateral);
    }

    public static Avancado parseControlador(String input, boolean lateral){

        String[] campos = input.split(",");
        String nome = campos[0];
        Integer id = Integer.parseInt(campos[1]);
        Map<Double, List<Atributo>> mapa = new HashMap<>();

        Velocidade velocidade = new Velocidade(Integer.parseInt(campos[2]));
        Jogador.addToMapa(velocidade,0.1,mapa);
        Resistencia resistencia = new Resistencia(Integer.parseInt(campos[3]));
        Jogador.addToMapa(resistencia,0.07,mapa);
        Destreza destreza = new Destreza(Integer.parseInt(campos[4]));
        Impulsao impulso = new Impulsao(Integer.parseInt(campos[5]));
        JogoDeCabeca jogocabeca = new JogoDeCabeca(Integer.parseInt(campos[6]));
        Remate remate = new Remate(Integer.parseInt(campos[7]));
        CapacidadeDePasse capacidadePasse = new CapacidadeDePasse(Integer.parseInt(campos[8]));
        Jogador.addToMapa(capacidadePasse,0.07,mapa);

        CapacidadeDefensiva capacidadeDefensiva = new CapacidadeDefensiva(Integer.parseInt(campos[9]));
        Jogador.addToMapa(capacidadeDefensiva,0.01,mapa);
        Motivacao motivacao = new Motivacao(50);
        Jogador.addToMapa(motivacao,0.05,mapa);
        Posicionamento posicionamento = new Posicionamento(Integer.parseInt(campos[10]));
        Jogador.addToMapa(posicionamento,0.05,mapa);
        int finalizacao = Integer.parseInt(campos[11]);

        if (lateral){
            Jogador.addToMapa(remate, 0.15, mapa);
            Jogador.addToMapa(jogocabeca, 0.05, mapa);
            Jogador.addToMapa(impulso, 0.05, mapa);
            Jogador.addToMapa(destreza, 0.1, mapa);
            Cruzamento cruzamento = new Cruzamento(Integer.parseInt(campos[12]));
            Jogador.addToMapa(cruzamento, 0.1, mapa);
        }
        else {
            Jogador.addToMapa(remate, 0.2, mapa);
            Jogador.addToMapa(jogocabeca, 0.1, mapa);
            Jogador.addToMapa(impulso, 0.1, mapa);
            Jogador.addToMapa(destreza, 0.05, mapa);
        }

        List<String> historico = new ArrayList<>();

        return new Avancado(nome, id, mapa, historico, finalizacao, lateral);

    }

    //              Getters and Setters             //

    public int getFinalizacao() {
        return finalizacao;
    }

    public void setFinalizacao(int finalizacao) {
        this.finalizacao = finalizacao;
    }

    public boolean isLateral() {
        return lateral;
    }

    public void setLateral(boolean lateral) {
        this.lateral = lateral;
    }

    public int habilidadeGeralEspecifica() {
        return (int) (super.habilidadeGeral() + (this.finalizacao * 0.2));
    }

    public Jogador clone() {
        return new Avancado(this);
    }

    public List<String> infoJogador (){
        List<String> list = super.infoJogador();
        if (lateral)
            list.set(2,"Posição: Avancado Lateral");
        else
            list.set(2,"Posição: Avancado Central");
        list.add("Finalização:" + finalizacao);
        list.add("");
        list.add("Overall:" + habilidadeGeralEspecifica());
        return list;
    }

    @Override
    public String getPosition() {
        String str = lateral ? "L" : "C";
        return "A" + str;
    }
}
