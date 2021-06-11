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
        this.lateral = defesa.isLateral();
    }

    public static Defesa parse(String input, boolean lateral){

        String[] campos = input.split(",");
        String nome = campos[0];
        Integer id = Integer.parseInt(campos[1]);
        Map<Double, List<Atributo>> mapa = new HashMap<>();

        Velocidade velocidade = new Velocidade(Integer.parseInt(campos[2]));
        Resistencia resistencia = new Resistencia(Integer.parseInt(campos[3]));
        Jogador.addToMapa(resistencia,0.07,mapa);
        Destreza destreza = new Destreza(Integer.parseInt(campos[4]));
        Jogador.addToMapa(destreza, 0.05, mapa);
        Impulsao impulso = new Impulsao(Integer.parseInt(campos[5]));
        JogoDeCabeca jogocabeca = new JogoDeCabeca(Integer.parseInt(campos[6]));
        Remate remate = new Remate(Integer.parseInt(campos[7]));
        Jogador.addToMapa(remate, 0.01, mapa);
        CapacidadeDePasse capacidadePasse = new CapacidadeDePasse(Integer.parseInt(campos[8]));

        int total = 0;
        for (int i = 2; i < 9; i++){
            total += Integer.parseInt(campos[i]);
        }
        double media = (double) total/7;
        int high = (int) media + 10;
        int low = (int) media - 10;
        int marcacao = Jogador.randomBetween(low, high);

        CapacidadeDefensiva capacidadeDefensiva = new CapacidadeDefensiva(Jogador.randomBetween(low,high));
        Jogador.addToMapa(capacidadeDefensiva,0.15,mapa);
        Motivacao motivacao = new Motivacao(50);
        Jogador.addToMapa(motivacao,0.05,mapa);
        Posicionamento posicionamento = new Posicionamento(Jogador.randomBetween(low,high));
        Jogador.addToMapa(posicionamento,0.1,mapa);

        if (lateral){
            Jogador.addToMapa(jogocabeca, 0.05, mapa);
            Jogador.addToMapa(impulso, 0.05, mapa);
            Jogador.addToMapa(velocidade, 0.1, mapa);
            Jogador.addToMapa(capacidadePasse, 0.07, mapa);
            Cruzamento cruzamento = new Cruzamento(Integer.parseInt(campos[9]));
            Jogador.addToMapa(cruzamento, 0.1, mapa);
        }
        else {
            Jogador.addToMapa(jogocabeca, 0.1, mapa);
            Jogador.addToMapa(impulso, 0.1, mapa);
            Jogador.addToMapa(velocidade, 0.07, mapa);
            Jogador.addToMapa(capacidadePasse, 0.1, mapa);
        }

        List<String> historico = new ArrayList<>();

        return new Defesa(nome, id, mapa, historico, marcacao, lateral);
    }

    public static Defesa parseControlador(String input, boolean lateral){

        String[] campos = input.split(",");
        String nome = campos[0];
        Integer id = Integer.parseInt(campos[1]);
        Map<Double, List<Atributo>> mapa = new HashMap<>();

        Velocidade velocidade = new Velocidade(Integer.parseInt(campos[2]));
        Resistencia resistencia = new Resistencia(Integer.parseInt(campos[3]));
        Jogador.addToMapa(resistencia,0.07,mapa);
        Destreza destreza = new Destreza(Integer.parseInt(campos[4]));
        Jogador.addToMapa(destreza, 0.05, mapa);
        Impulsao impulso = new Impulsao(Integer.parseInt(campos[5]));
        JogoDeCabeca jogocabeca = new JogoDeCabeca(Integer.parseInt(campos[6]));
        Remate remate = new Remate(Integer.parseInt(campos[7]));
        Jogador.addToMapa(remate, 0.01, mapa);
        CapacidadeDePasse capacidadePasse = new CapacidadeDePasse(Integer.parseInt(campos[8]));


        CapacidadeDefensiva capacidadeDefensiva = new CapacidadeDefensiva(Integer.parseInt(campos[9]));
        Jogador.addToMapa(capacidadeDefensiva,0.15,mapa);
        Motivacao motivacao = new Motivacao(50);
        Jogador.addToMapa(motivacao,0.05,mapa);
        Posicionamento posicionamento = new Posicionamento(Integer.parseInt(campos[10]));
        Jogador.addToMapa(posicionamento,0.1,mapa);

        int marcacao = Integer.parseInt(campos[11]);

        if (lateral){
            Jogador.addToMapa(jogocabeca, 0.05, mapa);
            Jogador.addToMapa(impulso, 0.05, mapa);
            Jogador.addToMapa(velocidade, 0.1, mapa);
            Jogador.addToMapa(capacidadePasse, 0.07, mapa);
            Cruzamento cruzamento = new Cruzamento(Integer.parseInt(campos[12]));
            Jogador.addToMapa(cruzamento, 0.1, mapa);
        }
        else {
            Jogador.addToMapa(jogocabeca, 0.1, mapa);
            Jogador.addToMapa(impulso, 0.1, mapa);
            Jogador.addToMapa(velocidade, 0.07, mapa);
            Jogador.addToMapa(capacidadePasse, 0.1, mapa);
        }

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

    public boolean isLateral() {
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


    public List<String> infoJogador (){
        List<String> list = super.infoJogador();
        if (lateral)
            list.set(2,"Posição: Defesa Lateral");
        else
            list.set(2,"Posição: Defesa Central");
        list.add("Marcação:" + marcacao);
        list.add("");
        list.add("Overall:" + habilidadeGeralEspecifica());
        return list;
    }

    @Override
    public String getPosition() {
        String str = lateral ? "L" : "C";
        return "D" + str ;
    }
}
