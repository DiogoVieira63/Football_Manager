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

    public GuardaRedes parseGuardaRedes(String input){

        //meti valores default nos atributos não dados pelos stores

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

        Fisico fisico = new Fisico(velocidade, resistencia);
        Tecnico tecnico = new Tecnico(capacidadePasse, destreza);
        Defensivo defensivo = new Defensivo(impulso, 50);
        Atacante atacante = new Atacante(remate, jogocabeca);
        MentalTatico mentalTatico = new MentalTatico(50, 50);

        List<Atributo> bestAtributos = new ArrayList<>();
        List<Atributo> atributos = new ArrayList<>();

        bestAtributos.add(mentalTatico);
        atributos.add(fisico);
        atributos.add(tecnico);
        atributos.add(defensivo);
        atributos.add(atacante);

        Map<Double, List<Atributo>> mapa = new HashMap<>();
        List<String> historico = new ArrayList<>();

        mapa.put(0.25, bestAtributos);
        mapa.put(0.1, atributos);

        return new GuardaRedes(nome, id, mapa, historico, elasticidade, 50);
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
        return (int) (super.habilidadeGeral() + (this.elasticidade * 0.20 + this.reflexos * 0.15));
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
