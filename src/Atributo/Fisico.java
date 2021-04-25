package Atributo;

public class Fisico implements Atributo{
    private int velocidade;
    private int resistencia;

    public Fisico(int velocidade, int resistencia) {
        this.velocidade = velocidade;
        this.resistencia = resistencia;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    @Override
    public double media() {
        return (double) (resistencia + velocidade )/2;
    }
}
