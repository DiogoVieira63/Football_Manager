import java.util.List;
import java.util.Objects;

public class GuardaRedes extends Jogador {
    private int elasticidade;

    public GuardaRedes (){
        super();
        this.elasticidade = 70;
        setPosition(Position.GUARDA_REDES);
    }

    public GuardaRedes(String name, Position position, Atributos atributos, List<String> historico,int elasticidade) {
        super(name,position,atributos,historico);
        this.elasticidade = elasticidade;
    }

    public int getElasticidade() {
        return elasticidade;
    }

    public void setElasticidade(int elasticidade) {
        this.elasticidade = elasticidade;
    }

    @Override
    public String toString() {
        return "Jogador{" +
                "\n name='" + super.getName() + '\'' +
                "\n position=" + super.getPosition() +
                "\n Atributos{\n" +super.getAtributos().toString() +
                "\n  elasticidade=" + getElasticidade() +
                "\n Histórico de Clubes=" + super.getHistorico().toString() +
                "\n}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        GuardaRedes that = (GuardaRedes) o;
        return getElasticidade() == that.getElasticidade();
    }

    public int habilidadeGeral (){
        return (int) (getAtributos().getTecnico().media() * 0.05
                + getAtributos().getFisico().media() * 0.10
                + getAtributos().getDefensivo().media() * 0.10
                + elasticidade * 0.80);
    }

}
