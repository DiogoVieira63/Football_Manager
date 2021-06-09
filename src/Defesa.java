import Atributo.*;

import java.io.Serializable;
import java.util.*;

public class Defesa extends Jogador implements Serializable {
    private static final long serialVersionUID = -4994171854769561001L;
    private int marcacao;
    private boolean lateral;

    //              Constructors                //

    public Defesa(){
        super();
        this.marcacao = 0;
        this.lateral = false;
    }

    public Defesa(String name, Integer id, Map<Double, List<Atributo>> atributos,
                  List<String> historico, int marcacao, boolean lateral)
    {
        super(name, id, atributos, historico);
        this.marcacao = marcacao;
        this.lateral = lateral;
    }

    public Defesa(Defesa defesa){
        super(defesa);
        this.marcacao = defesa.getMarcacao();
        this.lateral = defesa.getLateral();
    }

    public static Defesa parse(String input, boolean lateral){

        String[] campos = input.split(",");
        String nome = campos[0];
        int id = Integer.parseInt(campos[1]);
        int velocidade = Integer.parseInt(campos[2]);
        int resistencia = Integer.parseInt(campos[3]);
        int destreza = Integer.parseInt(campos[4]);
        int impulso = Integer.parseInt(campos[5]);
        int jogocabeca = Integer.parseInt(campos[6]);
        int remate = Integer.parseInt(campos[7]);
        int capacidadePasse = Integer.parseInt(campos[8]);
        Random rand = new Random();
        int marcacao = rand.nextInt(100);

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

        if (lateral){
            Cruzamento cruzamentoA = new Cruzamento(rand.nextInt(100));
            atributos1.add(remateA);
            atributos2.add(jogoDeCabecaA);
            atributos2.add(impulsaoA);
            atributos2.add(destrezaA);
            atributos2.add(motivacaoA);
            atributos3.add(resistenciaA);
            atributos3.add(capacidadeDePasseA);
            atributos4.add(velocidadeA);
            atributos4.add(posicionamentoA);
            atributos4.add(cruzamentoA);
            atributos5.add(capacidadeDePasseA);
        }
        else {
            atributos1.add(remateA);
            atributos2.add(destrezaA);
            atributos2.add(motivacaoA);
            atributos3.add(velocidadeA);
            atributos3.add(resistenciaA);
            atributos4.add(jogoDeCabecaA);
            atributos4.add(impulsaoA);
            atributos4.add(capacidadeDePasseA);
            atributos4.add(posicionamentoA);
            atributos5.add(capacidadeDefensivaA);
        }

        Map<Double, List<Atributo>> mapa = new HashMap<>();
        mapa.put(0.01, atributos1);
        mapa.put(0.05, atributos2);
        mapa.put(0.07, atributos3);
        mapa.put(0.1, atributos4);
        mapa.put(0.15, atributos5);

        List<String> historico = new ArrayList<>();

        return new Defesa(nome, id, mapa, historico, marcacao, lateral);
    }

    //              Getters and Setters             //

    public int getMarcacao() {
        return marcacao;
    }

    public void setMarcacao(int marcacao) {
        this.marcacao = marcacao;
    }

    public boolean getLateral() {
        return lateral;
    }

    public void setLateral(boolean lateral) {
        this.lateral = lateral;
    }

    public int habilidadeGeralEspecifica() {
        return (int) (super.habilidadeGeral() + (this.marcacao * 0.2));
    }

    public Jogador clone() {
        return new Defesa(this);
    }


    public List<Object> infoJogador (){
        List<Object> list = super.infoJogador();
        if (lateral)
            list.set(2,Map.entry("Posição:","Defesa Lateral"));
        else
            list.set(2,Map.entry("Posição:","Defesa Central"));
        list.add(Map.entry("Marcação:",marcacao));
        list.add(Map.entry("",""));
        list.add(Map.entry("Overall:",habilidadeGeralEspecifica()));
        return list;
    }
}
