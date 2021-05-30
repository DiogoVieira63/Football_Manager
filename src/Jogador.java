import Atributo.Atributo;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String name;
    private Position position;
    private AbstractMap.SimpleEntry<Double, Atributo.Atributo> atributos;
    private List<String> historico;

    public Jogador(){
        this.name = "Default";
        this.position = Position.MEDIO;
        this.atributos = new ArrayList<>();
        this.historico = new ArrayList<>();
    }

    public Jogador(String name, Position position, AbstractMap.SimpleEntry<Double, Atributo.Atributo> atributos, List<String> historico) {
        this.name = name;
        this.position = position;
        this.atributos = atributos;
        this.historico = historico;
    }

    public Jogador (Jogador jogador){
        this.name = jogador.getName();
        this.position = jogador.getPosition();
        this.atributos= jogador.getAtributos();
        setHistorico(jogador.getHistorico());
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "\n name='" + name + '\'' +
                "\n position=" + position +
                "\n Atributos{\n" +atributos.toString() +
                "\n Histórico de Clubes=" +historico.toString() +
                "}";
    }

    public void addEquipaHistorico (String equipa){
        historico.add(equipa);
    }

    /**
     * Not done yet. Default general rating
     * @return Rating
     */

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public AbstractMap.SimpleEntry<Double, Atributo.Atributo> getAtributos() {
        return atributos;
    }

    public void setAtributos(AbstractMap.SimpleEntry<Double, Atributo.Atributo> atributos) {
        this.atributos = atributos;
    }

    public List<String> getHistorico() {
        return historico;
    }

    public void setHistorico(List<String> historico) {
        this.historico = new ArrayList<>();
        for (String equipa : historico){
            this.historico.add(equipa);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jogador jogador = (Jogador) o;
        return getName().equals(jogador.getName()) && getPosition() == jogador.getPosition() && getAtributos().equals(jogador.getAtributos()) && (getHistorico().equals(jogador.getHistorico()));
    }

    @Override
    public Jogador clone (){
        return new Jogador(this);
    }

}
