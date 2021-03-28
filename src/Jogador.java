import java.util.List;

public class Jogador {
    private String name;
    private Position position;
    private Atributos atributos;
    private List<String> historico;

    public Jogador(){
        this.name = "Default";
        this.position = Position.MEDIO;
        this.atributos = new Atributos();
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "\n name='" + name + '\'' +
                "\n position=" + position +
                "\n atributos= " +atributos.toString();
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
}
