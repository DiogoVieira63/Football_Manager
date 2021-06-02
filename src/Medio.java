import Atributo.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    public static Medio parse(String input){

        //meti valores atoa

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

        Fisico fisico = new Fisico(velocidade, resistencia);
        Tecnico tecnico = new Tecnico(capacidadePasse, destreza);
        Defensivo defensivo = new Defensivo(impulso, 50);
        Atacante atacante = new Atacante(remate, jogocabeca);
        MentalTatico mentalTatico = new MentalTatico(50, 50);

        List<Atributo> bestAtributos = new ArrayList<>();
        List<Atributo> atributos = new ArrayList<>();

        bestAtributos.add(tecnico);
        bestAtributos.add(fisico);
        atributos.add(defensivo);
        atributos.add(atacante);
        atributos.add(mentalTatico);

        Map<Double, List<Atributo>> mapa = new HashMap<>();
        List<String> historico = new ArrayList<>();

        mapa.put(0.125, bestAtributos);
        mapa.put(0.1, atributos);

        return new Medio(nome, id, mapa, historico, recuperacaoBoLA, 50, false);
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
        return (int) (super.habilidadeGeral() + (this.recuperacaoBola * 0.20 + this.visao * 0.15));
    }

    public Jogador clone() {
        return new Medio(this);
    }
}
