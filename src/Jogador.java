import Atributo.Atributo;

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
        return atributos;
    }

    public void setAtributos(Map<Double, List<Atributo>> atributos) {
        this.atributos = atributos;
    }

    public List<String> getHistorico() {
        return historico;
    }

    public void setHistorico(List<String> historico) {
        this.historico = historico;
    }

    public void addEquipaHistorico (String equipa){
        historico.add(equipa);
    }

    //              Abstract Methods                //

    public abstract int habilidadeGeral();

    public abstract Jogador clone();

    /*
    public int habilidadeGeral (){
        int result = 0;
        switch(getPosition().getNum()) {
            case 1:
                result = (int) (atributos.getAtaque().media() * 0.15
                                        + atributos.getTecnico().media() * 0.25
                                        + atributos.getFisico().media() * 0.25
                                        + atributos.getDefensivo().media() * 0.35);
            break;
            case 2:
                result = (int) (atributos.getAtaque().media() * 0.20
                        + atributos.getTecnico().media() * 0.30
                        + atributos.getFisico().media() * 0.30
                        + atributos.getDefensivo().media() * 0.20);
            break;
            case 3:
                result = (int) (atributos.getAtaque().media() * 0.40
                        + atributos.getTecnico().media() * 0.25
                        + atributos.getFisico().media() * 0.25
                        + atributos.getDefensivo().media() * 0.10);
                break;
        }
        return result;
    }
     */

}
