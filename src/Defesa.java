import Atributo.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Defesa extends Jogador{
    private int marcacao;
    private boolean lateral;

    //              Constructors                //

    public Defesa(){
        super();
        this.marcacao = 0;
        this.lateral = false;
    }

    public Defesa(String name, String id, Map<Double, List<Atributo>> atributos,
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

    public static Defesa parse(String input){


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
        int marcacaO = Integer.parseInt(campos[9]);

        Fisico fisico = new Fisico(velocidade, resistencia);
        Tecnico tecnico = new Tecnico(capacidadePasse, destreza);
        Defensivo defensivo = new Defensivo(impulso, 50);
        Atacante atacante = new Atacante(remate, jogocabeca);
        MentalTatico mentalTatico = new MentalTatico(50, 50);

        List<Atributo> bestAtributos = new ArrayList<>();
        List<Atributo> atributos = new ArrayList<>();

        bestAtributos.add(defensivo);
        bestAtributos.add(mentalTatico);
        atributos.add(atacante);
        atributos.add(tecnico);
        atributos.add(fisico);

        Map<Double, List<Atributo>> mapa = new HashMap<>();
        List<String> historico = new ArrayList<>();

        mapa.put(0.125, bestAtributos);
        mapa.put(0.1, atributos);

        return new Defesa(nome, id, mapa, historico, marcacaO, true);
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
        return (int) (super.habilidadeGeral() + (this.marcacao * 0.3));
    }

    public Jogador clone() {
        return new Defesa(this);
    }
}
