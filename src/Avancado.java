import Atributo.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static Avancado parse(String input){


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
        int finalizacaO = Integer.parseInt(campos[9]);

        Fisico fisico = new Fisico(velocidade, resistencia);
        Tecnico tecnico = new Tecnico(capacidadePasse, destreza);
        Defensivo defensivo = new Defensivo(impulso, 50);
        Atacante atacante = new Atacante(remate, jogocabeca);
        MentalTatico mentalTatico = new MentalTatico(50, 50);

        List<Atributo> bestAtributos = new ArrayList<>();
        List<Atributo> atributos = new ArrayList<>();

        bestAtributos.add(atacante);
        bestAtributos.add(fisico);
        atributos.add(defensivo);
        atributos.add(tecnico);
        atributos.add(mentalTatico);

        Map<Double, List<Atributo>> mapa = new HashMap<>();
        List<String> historico = new ArrayList<>();

        mapa.put(0.125, bestAtributos);
        mapa.put(0.1, atributos);

        return new Avancado(nome, id, mapa, historico, finalizacaO, true);
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
        return (int) (super.habilidadeGeral() + (this.finalizacao * 0.3));
    }

    public Jogador clone() {
        return new Avancado(this);
    }
}
