import Atributo.*;

import java.io.Serializable;
import java.rmi.MarshalledObject;
import java.util.*;

public class GuardaRedes extends Jogador implements Serializable {
    private static final long serialVersionUID = -8649963430663159313L;
    private int elasticidade;
    private int reflexos;

    //              Constructors                //

    public GuardaRedes (){
        super();
        this.elasticidade = 0;
        this.reflexos = 0;
    }

    public GuardaRedes(String name, Integer id, Map<Double, List<Atributo>> atributos,
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

        String[] campos = input.split(",");
        String nome = campos[0];
        Integer id = Integer.parseInt(campos[1]);
        Map<Double, List<Atributo>> mapa = new HashMap<>();
        Velocidade velocidade = new Velocidade(Integer.parseInt(campos[2]));
        Jogador.addToMapa(velocidade,0.01,mapa);
        Resistencia resistencia = new Resistencia(Integer.parseInt(campos[3]));
        Jogador.addToMapa(resistencia,0.0075,mapa);
        Destreza destreza = new Destreza(Integer.parseInt(campos[4]));
        Jogador.addToMapa(destreza,0.0075,mapa);
        Impulsao impulso = new Impulsao(Integer.parseInt(campos[5]));
        Jogador.addToMapa(impulso,0.05,mapa);
        JogoDeCabeca jogocabeca = new JogoDeCabeca(Integer.parseInt(campos[6]));
        Jogador.addToMapa(jogocabeca,0.005,mapa);
        Remate remate = new Remate(Integer.parseInt(campos[7]));
        Jogador.addToMapa(remate,0.005,mapa);
        CapacidadeDePasse capacidadePasse = new CapacidadeDePasse(Integer.parseInt(campos[8]));
        Jogador.addToMapa(capacidadePasse,0.01,mapa);
        int elasticidade = Integer.parseInt(campos[9]);

        int total = 0;
        for (int i = 2; i< 10; i++){
            total += Integer.parseInt(campos[i]);
        }
        double media = (double) total/8;

        int high = (int) media + 10;
        int low = (int) media - 10;
        int reflexos = Jogador.randomBetween(low,high);
        CapacidadeDefensiva capacidadeDefensiva = new CapacidadeDefensiva(Jogador.randomBetween(low,high));
        Jogador.addToMapa(capacidadeDefensiva,0.01,mapa);
        Motivacao motivacao= new Motivacao(50);
        Jogador.addToMapa(motivacao,0.05,mapa);
        Posicionamento posicionamento = new Posicionamento(Jogador.randomBetween(low,high));
        Jogador.addToMapa(posicionamento,0.10,mapa);

        List<String> historico = new ArrayList<>();

        return new GuardaRedes(nome, id, mapa, historico, elasticidade, reflexos);
    }

    public static GuardaRedes parseControlador(String input){

        String[] campos = input.split(",");
        String nome = campos[0];
        Integer id = Integer.parseInt(campos[1]);
        Map<Double, List<Atributo>> mapa = new HashMap<>();
        Velocidade velocidade = new Velocidade(Integer.parseInt(campos[2]));
        Jogador.addToMapa(velocidade,0.01,mapa);
        Resistencia resistencia = new Resistencia(Integer.parseInt(campos[3]));
        Jogador.addToMapa(resistencia,0.0075,mapa);
        Destreza destreza = new Destreza(Integer.parseInt(campos[4]));
        Jogador.addToMapa(destreza,0.0075,mapa);
        Impulsao impulso = new Impulsao(Integer.parseInt(campos[5]));
        Jogador.addToMapa(impulso,0.05,mapa);
        JogoDeCabeca jogocabeca = new JogoDeCabeca(Integer.parseInt(campos[6]));
        Jogador.addToMapa(jogocabeca,0.005,mapa);
        Remate remate = new Remate(Integer.parseInt(campos[7]));
        Jogador.addToMapa(remate,0.005,mapa);
        CapacidadeDePasse capacidadePasse = new CapacidadeDePasse(Integer.parseInt(campos[8]));
        Jogador.addToMapa(capacidadePasse,0.01,mapa);
        int elasticidade = Integer.parseInt(campos[9]);
        CapacidadeDefensiva capacidadeDefensiva = new CapacidadeDefensiva(Integer.parseInt(campos[10]));
        Jogador.addToMapa(capacidadeDefensiva, 0.01, mapa);
        Motivacao motivacao = new Motivacao(50);
        Jogador.addToMapa(motivacao,0.05, mapa);
        Posicionamento posicionamento = new Posicionamento(Integer.parseInt(campos[11]));
        Jogador.addToMapa(posicionamento,0.10, mapa);
        int reflexos = Integer.parseInt(campos[12]);

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


    public List<String> infoJogador (){
        List<String> list = super.infoJogador();
        list.add("Elasticidade:" + elasticidade);
        list.add("Reflexos:" +reflexos);
        list.add("");
        list.add("Overall:" +habilidadeGeralEspecifica());
        return list;
    }
    /*
    public String toString(){
        String jog = super.toString();
    }
    */
    @Override
    public String getPosition() {
        return "GR";
    }

}
