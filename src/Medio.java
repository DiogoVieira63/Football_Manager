import Atributo.*;

import java.util.*;

public class Medio extends Jogador{
    private int recuperacaoBola;
    private int visao;
    private boolean lateral;

    //              Constructors                //

    public Medio(){
        super();
        this.recuperacaoBola = 0;
        this.visao = 0;
        this.lateral = false;
    }

    public Medio(String name, String id, Map<Double, List<Atributo>> atributos,
                 List<String> historico, int recuperacaoBola, int visao, boolean lateral)
    {
        super(name, id, atributos, historico);
        this.recuperacaoBola = recuperacaoBola;
        this.visao = visao;
        this.lateral = lateral;
    }

    public Medio(Medio medio){
        super(medio);
        this.recuperacaoBola = medio.getRecuperacaoBola();
        this.visao = medio.getVisao();
        this.lateral = medio.getLateral();
    }

    public static Medio parse(String input, boolean lateral){

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
        int recuperacaoBoLA = Integer.parseInt(campos[9]);

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

        List<Atributo> atributos1 = new ArrayList<>(); // 0.03
        List<Atributo> atributos2 = new ArrayList<>(); // 0.05
        List<Atributo> atributos3 = new ArrayList<>(); // 0.1
        List<Atributo> atributos4 = new ArrayList<>(); // 0.12
        List<Atributo> atributos5 = new ArrayList<>(); // 0.15

        Map<Double, List<Atributo>> mapa = new HashMap<>();

        if (lateral){
            Cruzamento cruzamentoA = new Cruzamento(rand.nextInt(100));
            atributos1.add(impulsaoA);
            atributos2.add(remateA);
            atributos2.add(jogoDeCabecaA);
            atributos2.add(capacidadeDefensivaA);
            atributos2.add(posicionamentoA);
            atributos2.add(motivacaoA);
            atributos3.add(velocidadeA);
            atributos3.add(resistenciaA);
            atributos3.add(destrezaA);
            atributos3.add(cruzamentoA);
            atributos4.add(capacidadeDePasseA);
            mapa.put(0.03, atributos1);
            mapa.put(0.05, atributos2);
            mapa.put(0.1, atributos3);
            mapa.put(0.12, atributos4);
        }
        else {
            atributos2.add(jogoDeCabecaA);
            atributos2.add(impulsaoA);
            atributos2.add(capacidadeDefensivaA);
            atributos2.add(posicionamentoA);
            atributos2.add(motivacaoA);
            atributos3.add(remateA);
            atributos3.add(velocidadeA);
            atributos3.add(resistenciaA);
            atributos3.add(destrezaA);
            atributos5.add(capacidadeDePasseA);
            mapa.put(0.05, atributos2);
            mapa.put(0.1, atributos3);
            mapa.put(0.15, atributos5);
        }

        List<String> historico = new ArrayList<>();

        return new Medio(nome, id, mapa, historico, recuperacaoBoLA, 50, lateral);
    }

    //              Getters and Setters             //

    public int getRecuperacaoBola() {
        return recuperacaoBola;
    }

    public void setRecuperacaoBola(int recuperacaoBola) {
        this.recuperacaoBola = recuperacaoBola;
    }

    public int getVisao() {
        return visao;
    }

    public void setVisao(int visao) {
        this.visao = visao;
    }

    public boolean getLateral() {
        return lateral;
    }

    public void setLateral(boolean lateral) {
        this.lateral = lateral;
    }

    public int habilidadeGeralEspecifica() {
        return (int) (super.habilidadeGeral() + (this.recuperacaoBola * 0.1 + this.visao * 0.1));
    }

    public Jogador clone() {
        return new Medio(this);
    }
}
