import Atributo.*;

import java.io.Serializable;
import java.util.*;

public class Medio extends Jogador implements Serializable {
    private static final long serialVersionUID = -5533892077751032795L;
    private int recuperacaoBola;
    private boolean lateral;

    //              Constructors                //

    public Medio(){
        super();
        this.recuperacaoBola = 0;
        this.lateral = false;
    }

    public Medio(String name, Integer id, Map<Double, List<Atributo>> atributos,
                 List<String> historico, int recuperacaoBola, boolean lateral)
    {
        super(name, id, atributos, historico);
        this.recuperacaoBola = recuperacaoBola;
        this.lateral = lateral;
    }

    public Medio(Medio medio){
        super(medio);
        this.recuperacaoBola = medio.getRecuperacaoBola();
        this.lateral = medio.isLateral();
    }

    public static Medio parse(String input, boolean lateral){

        String[] campos = input.split(",");
        String nome = campos[0];
        Integer id = Integer.parseInt(campos[1]);
        Map<Double, List<Atributo>> mapa = new HashMap<>();

        Velocidade velocidade = new Velocidade(Integer.parseInt(campos[2]));
        Jogador.addToMapa(velocidade,0.1,mapa);
        Resistencia resistencia = new Resistencia(Integer.parseInt(campos[3]));
        Jogador.addToMapa(resistencia,0.1,mapa);
        Destreza destreza = new Destreza(Integer.parseInt(campos[4]));
        Jogador.addToMapa(destreza,0.1,mapa);
        Impulsao impulso = new Impulsao(Integer.parseInt(campos[5]));
        JogoDeCabeca jogocabeca = new JogoDeCabeca(Integer.parseInt(campos[6]));
        Jogador.addToMapa(jogocabeca,0.05,mapa);
        Remate remate = new Remate(Integer.parseInt(campos[7]));
        CapacidadeDePasse capacidadePasse = new CapacidadeDePasse(Integer.parseInt(campos[8]));
        int campo9 = Integer.parseInt(campos[9]);

        int total = 0;
        for (int i = 2; i< 10; i++){
            total += Integer.parseInt(campos[i]);
        }
        double media = (double) total/8;

        int high = (int) media + 10;
        int low = (int) media - 10;

        CapacidadeDefensiva capacidadeDefensiva = new CapacidadeDefensiva(Jogador.randomBetween(low,high));
        Jogador.addToMapa(capacidadeDefensiva,0.05,mapa);
        Motivacao motivacao = new Motivacao(50);
        Jogador.addToMapa(motivacao,0.05,mapa);
        Posicionamento posicionamento = new Posicionamento(Jogador.randomBetween(low,high));
        Jogador.addToMapa(posicionamento,0.05,mapa);

        int recuperacaoBola;

        if (lateral){
            Jogador.addToMapa(remate, 0.05, mapa);
            Jogador.addToMapa(impulso, 0.03, mapa);
            Jogador.addToMapa(capacidadePasse, 0.12, mapa);
            recuperacaoBola = Jogador.randomBetween(low,high);
            Cruzamento cruzamento = new Cruzamento(campo9);
            Jogador.addToMapa(cruzamento, 0.1, mapa);
        }
        else {
            Jogador.addToMapa(remate, 0.1, mapa);
            Jogador.addToMapa(impulso, 0.05, mapa);
            Jogador.addToMapa(capacidadePasse, 0.15, mapa);
            recuperacaoBola = campo9;
        }

        List<String> historico = new ArrayList<>();

        return new Medio(nome, id, mapa, historico, recuperacaoBola, lateral);
    }

    public static Medio parseControlador(String input, boolean lateral){

        String[] campos = input.split(",");
        String nome = campos[0];
        Integer id = Integer.parseInt(campos[1]);
        Map<Double, List<Atributo>> mapa = new HashMap<>();

        Velocidade velocidade = new Velocidade(Integer.parseInt(campos[2]));
        Jogador.addToMapa(velocidade,0.1,mapa);
        Resistencia resistencia = new Resistencia(Integer.parseInt(campos[3]));
        Jogador.addToMapa(resistencia,0.1,mapa);
        Destreza destreza = new Destreza(Integer.parseInt(campos[4]));
        Jogador.addToMapa(destreza,0.1,mapa);
        Impulsao impulso = new Impulsao(Integer.parseInt(campos[5]));
        JogoDeCabeca jogocabeca = new JogoDeCabeca(Integer.parseInt(campos[6]));
        Jogador.addToMapa(jogocabeca,0.05,mapa);
        Remate remate = new Remate(Integer.parseInt(campos[7]));
        CapacidadeDePasse capacidadePasse = new CapacidadeDePasse(Integer.parseInt(campos[8]));

        CapacidadeDefensiva capacidadeDefensiva = new CapacidadeDefensiva(Integer.parseInt(campos[9]));
        Jogador.addToMapa(capacidadeDefensiva,0.05,mapa);
        Motivacao motivacao = new Motivacao(50);
        Jogador.addToMapa(motivacao,0.05,mapa);
        Posicionamento posicionamento = new Posicionamento(Integer.parseInt(campos[10]));
        Jogador.addToMapa(posicionamento,0.05,mapa);

        int recuperacaoBola = Integer.parseInt(campos[11]);

        if (lateral){
            Jogador.addToMapa(remate, 0.05, mapa);
            Jogador.addToMapa(impulso, 0.03, mapa);
            Jogador.addToMapa(capacidadePasse, 0.12, mapa);
            Cruzamento cruzamento = new Cruzamento(Integer.parseInt(campos[12]));
            Jogador.addToMapa(cruzamento, 0.1, mapa);
        }
        else {
            Jogador.addToMapa(remate, 0.1, mapa);
            Jogador.addToMapa(impulso, 0.05, mapa);
            Jogador.addToMapa(capacidadePasse, 0.15, mapa);
        }

        List<String> historico = new ArrayList<>();

        return new Medio(nome, id, mapa, historico, recuperacaoBola, lateral);
    }

    //              Getters and Setters             //

    public int getRecuperacaoBola() {
        return recuperacaoBola;
    }

    public void setRecuperacaoBola(int recuperacaoBola) {
        this.recuperacaoBola = recuperacaoBola;
    }

    public boolean isLateral() {
        return lateral;
    }

    public void setLateral(boolean lateral) {
        this.lateral = lateral;
    }

    public int habilidadeGeralEspecifica() {
        return (int) (super.habilidadeGeral() + (this.recuperacaoBola * 0.2));
    }

    public Jogador clone() {
        return new Medio(this);
    }

    public List<String> infoJogador (){
        List<String> list = super.infoJogador();
        if (lateral)
            list.set(2,"Posição: Medio Lateral");
        else
            list.set(2,"Posição: Medio Central");
        list.add("RecuperaçãoDeBola:" +recuperacaoBola);
        list.add("");
        list.add("Overall:" +habilidadeGeralEspecifica());
        return list;
    }

    @Override
    public String getPosition() {
        String str = lateral ? "L" : "C";
        return "M" + str ;
    }

}
