import Atributo.*;

import java.util.*;

public class Avancado extends Jogador {
    private int finalizacao;
    private boolean lateral;

    //              Constructors                //

    public Avancado(){
        super();
        this.finalizacao = 0;
        this.lateral = false;
    }

    public Avancado(String name, String id, Map<Double, List<Atributo>> atributos,
                    List<String> historico, int finalizacao, boolean lateral)
    {
        super(name, id, atributos, historico);
        this.finalizacao = finalizacao;
        this.lateral = lateral;
    }

    public Avancado(Avancado avancado){
        super(avancado);
        this.finalizacao = avancado.getFinalizacao();
        this.lateral = avancado.getLateral();
    }

    public static Avancado parse(String input, boolean lateral){

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
        int finalizacao = Integer.parseInt(campos[9]);
        Random rand = new Random();

        //                  Atributos dados                 //

        Velocidade velocidadeA = new Velocidade(velocidade);
        Resistencia resistenciaA = new Resistencia(resistencia);
        Destreza destrezaA = new Destreza(destreza);
        Impulsao impulsaoA = new Impulsao(impulso);
        JogoDeCabeca jogoDeCabecaA = new JogoDeCabeca(jogocabeca);
        Remate remateA = new Remate(remate);
        CapacidadeDePasse capacidadeDePasseA = new CapacidadeDePasse(capacidadePasse);

        //                  Atributos extras nossos         //

        CapacidadeDefensiva capacidadeDefensivaA = new CapacidadeDefensiva(rand.nextInt(100));
        Motivacao motivacaoA = new Motivacao(rand.nextInt(100));
        Posicionamento posicionamentoA = new Posicionamento(rand.nextInt(100));

        List<Atributo> atributos1 = new ArrayList<>(); // 0.01
        List<Atributo> atributos2 = new ArrayList<>(); // 0.05
        List<Atributo> atributos3 = new ArrayList<>(); // 0.07
        List<Atributo> atributos4 = new ArrayList<>(); // 0.1
        List<Atributo> atributos5 = new ArrayList<>(); // 0.15
        List<Atributo> atributos6 = new ArrayList<>(); // 0.2

        Map<Double, List<Atributo>> mapa = new HashMap<>();

        if (lateral) {
            Cruzamento cruzamentoA = new Cruzamento(rand.nextInt(100));
            atributos1.add(capacidadeDefensivaA);
            atributos2.add(jogoDeCabecaA);
            atributos2.add(impulsaoA);
            atributos2.add(posicionamentoA);
            atributos2.add(motivacaoA);
            atributos3.add(resistenciaA);
            atributos3.add(capacidadeDePasseA);
            atributos4.add(velocidadeA);
            atributos4.add(destrezaA);
            atributos4.add(cruzamentoA);
            atributos5.add(remateA);
            mapa.put(0.01, atributos1);
            mapa.put(0.05, atributos2);
            mapa.put(0.07, atributos3);
            mapa.put(0.1, atributos4);
            mapa.put(0.15, atributos5);
        }
        else {
            atributos1.add(capacidadeDefensivaA);
            atributos2.add(destrezaA);
            atributos2.add(posicionamentoA);
            atributos2.add(motivacaoA);
            atributos3.add(resistenciaA);
            atributos3.add(capacidadeDePasseA);
            atributos4.add(jogoDeCabecaA);
            atributos4.add(impulsaoA);
            atributos4.add(velocidadeA);
            atributos6.add(remateA);
            mapa.put(0.01, atributos1);
            mapa.put(0.05, atributos2);
            mapa.put(0.07, atributos3);
            mapa.put(0.1, atributos4);
            mapa.put(0.2, atributos6);
        }

        List<String> historico = new ArrayList<>();

        return new Avancado(nome, id, mapa, historico, finalizacao, true);
    }

    //              Getters and Setters             //

    public int getFinalizacao() {
        return finalizacao;
    }

    public void setFinalizacao(int finalizacao) {
        this.finalizacao = finalizacao;
    }

    public boolean getLateral() {
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
}
