import Atributo.*;

import java.util.*;
import java.util.stream.Collectors;

public abstract class Jogador {
    private String name;
    private String id; //número da camisola
    private Map<Double, List<Atributo>> atributos;
    private List<String> historico;

    //              Constructors                //

    public Jogador(){
        this.name = "";
        this.id = "";
        this.atributos = new HashMap<>();
        this.historico = new ArrayList<>();
    }

    public Jogador(String name, String id, Map<Double, List<Atributo>> atributos,
                   List<String> historico)
    {
        this.name = name;
        this.id = id;
        this.atributos = atributos
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        l -> l.getValue()
                                .stream()
                                .map(Atributo::clone)
                                .collect(Collectors.toList())));
        this.historico = new ArrayList<>(historico);
    }

    public Jogador(Jogador jogador){
        this.name = jogador.getName();
        this.id = jogador.getId();
        this.atributos = jogador.getAtributos();
        this.historico = jogador.getHistorico();
    }

    //              Getters and Setters             //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Map<Double, List<Atributo>> getAtributos() {
        return this.atributos
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        l -> l.getValue()
                                .stream()
                                .map(Atributo::clone)
                                .collect(Collectors.toList())));
    }

    public void setAtributos(Map<Double, List<Atributo>> atributos) {
        this.atributos = atributos
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        l -> l.getValue()
                                .stream()
                                .map(Atributo::clone)
                                .collect(Collectors.toList())));;
    }

    public List<String> getHistorico() {
        return new ArrayList<>(this.historico);
    }

    public void setHistorico(List<String> historico) {
        this.historico = new ArrayList<>(historico);
    }

    public void addEquipaHistorico (String equipa){
        historico.add(equipa);
    }

    public int habilidadeGeral() {
        double ataque = 0;
        double defesa = 0;
        double tecnico = 0;
        double fisico = 0;
        double mental = 0;
        for (Map.Entry<Double, List<Atributo>> entry : this.atributos.entrySet()){
            Double d = entry.getKey();
            for (Atributo atributo : entry.getValue()){
                if (atributo instanceof Atacante){
                    ataque = atributo.media() * d;
                }
                if (atributo instanceof Fisico){
                    fisico = atributo.media() * d;
                }
                if (atributo instanceof Tecnico){
                    tecnico = atributo.media() * d;
                }
                if (atributo instanceof Defensivo){
                    defesa = atributo.media() * d;
                }
                if (atributo instanceof MentalTatico){
                    mental = atributo.media() * d;
                }
            }
        }
        return (int) (ataque + fisico + tecnico + defesa + mental);
    }

    //              Abstract Methods                //

    public abstract int habilidadeGeralEspecifica();

    public abstract Jogador clone();

    /*
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Jogador: ").append(this.name);
        sb.append("\n");
        sb.append("Número da Camisola: ").append(this.id);
        sb.append("\n");
        sb.append("Atributos: ").append(this.atributos.values());
        sb.append("\n");
        sb.append("Histórico: ").append(this.historico);
        return sb.toString();
    }
    */
}
