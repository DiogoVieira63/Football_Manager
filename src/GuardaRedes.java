import Atributo.*;

import java.util.*;

public class GuardaRedes extends Jogador {
    private int elasticidade;
    private int reflexos;

    //              Constructors                //

    public GuardaRedes (){
        super();
        this.elasticidade = 0;
        this.reflexos = 0;
    }

    public GuardaRedes(String name, String id, Map<Double, List<Atributo>> atributos,
                       List<String> historico, int elasticidade, int reflexos){
        super(name, id, atributos, historico);
        this.elasticidade = elasticidade;
        this.reflexos = reflexos;
    }

    public GuardaRedes (GuardaRedes guardaRedes){
        super(guardaRedes);
        this.elasticidade = guardaRedes.getElasticidade();
        this.reflexos = guardaRedes.getReflexos();
    }

    public static GuardaRedes parse(String input){

        String[] campos = input.split(",", 9);
        String nome = campos[0];
        String id = campos[1];
        int velocidade = Integer.parseInt(campos[2]);
        int resistencia = Integer.parseInt(campos[3]);
        int destreza = Integer.parseInt(campos[4]);
        int impulso = Integer.parseInt(campos[5]);
        int jogocabeca = Integer.parseInt(campos[6]);
        int remate = Integer.parseInt(campos[7]);
        int capacidadePasse = Integer.parseInt(campos[8]);
        int elasticidade = Integer.parseInt(campos[9]);

        Random rand = new Random();
        int reflexos = rand.nextInt(100);

        //                  Atributos dados                 //

        Velocidade velocidadeA = new Velocidade(velocidade);
        Resistencia resistenciaA = new Resistencia(resistencia);
        Destreza destrezaA = new Destreza(destreza);
        Impulsao impulsaoA = new Impulsao(impulso);
        JogoDeCabeca jogoDeCabecaA = new JogoDeCabeca(jogocabeca);
        Remate remateA = new Remate(remate);
        CapacidadeDePasse capacidadeDePasseA = new CapacidadeDePasse(capacidadePasse);

        // os restantes

        CapacidadeDefensiva capacidadeDefensivaA = new CapacidadeDefensiva(rand.nextInt(100));
        Motivacao motivacaoA = new Motivacao(rand.nextInt(100));
        Posicionamento posicionamentoA = new Posicionamento(rand.nextInt(100));

        List<Atributo> atributos1 = new ArrayList<>(); // 0.005
        List<Atributo> atributos2 = new ArrayList<>(); // 0.0075
        List<Atributo> atributos3 = new ArrayList<>(); // 0.01
        List<Atributo> atributos4 = new ArrayList<>(); // 0.05
        List<Atributo> atributos5 = new ArrayList<>(); // 0.01

        atributos1.add(remateA);
        atributos1.add(jogoDeCabecaA);
        atributos2.add(resistenciaA);
        atributos2.add(destrezaA);
        atributos3.add(capacidadeDefensivaA);
        atributos3.add(velocidadeA);
        atributos3.add(capacidadeDePasseA);
        atributos4.add(impulsaoA);
        atributos4.add(motivacaoA);
        atributos5.add(posicionamentoA);

        Map<Double, List<Atributo>> mapa = new HashMap<>();

        List<String> historico = new ArrayList<>();

        return new GuardaRedes(nome, id, mapa, historico, elasticidade, reflexos);
    }

    //              Getters and Setters             //

    public int getElasticidade() {
        return elasticidade;
    }

    public void setElasticidade(int elasticidade) {
        this.elasticidade = elasticidade;
    }

    public int getReflexos() {
        return reflexos;
    }

    public void setReflexos(int reflexos) {
        this.reflexos = reflexos;
    }

    public int habilidadeGeralEspecifica (){
        return (int) (super.habilidadeGeral() + (this.elasticidade * 0.375 + this.reflexos * 0.375));
    }

    public Jogador clone(){
        return new GuardaRedes(this);
    }

    /*
    public String toString(){
        String jog = super.toString();
    }
    */

}
