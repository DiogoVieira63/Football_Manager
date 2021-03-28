public class GuardaRedes extends JogadorDeCampo {
    private int elasticidade;

    public GuardaRedes (){
        super();
        this.elasticidade = 70;
        setPosition(Position.GUARDAREDES);
    }

    public int getElasticidade() {
        return elasticidade;
    }

    public void setElasticidade(int elasticidade) {
        this.elasticidade = elasticidade;
    }

    @Override
    public String toString() {
        return super.toString() + "\n elasticidade=" + elasticidade;
    }
}
