import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Jogador {
    private String name;
    private Position position;
    private Atributos atributos;
    private List<String> historico;

    public Jogador(){
        this.name = "Default";
        this.position = Position.MEDIO;
        this.atributos = new Atributos();
        this.historico = new ArrayList<>();
    }

    public Jogador(String name, Position position, Atributos atributos, List<String> historico) {
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
        Atributos atributos = getAtributos();
        return
                (atributos.getCapacidadePasse()
                + atributos.getDestreza()
                + atributos.getImpulsao()
                + atributos.getRemate()
                + atributos.getJogoDecCabeca()
                + atributos.getResistencia()
                + atributos.getVelocidade())/7;
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

    public Atributos getAtributos() {
        return atributos;
    }

    public void setAtributos(Atributos atributos) {
        this.atributos = atributos;
    }

    public List<String> getHistorico() {
        return historico;
    }

    public void setHistorico(List<String> historico) {
        this.historico = new ArrayList<>();
        this.historico.addAll(historico);
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
