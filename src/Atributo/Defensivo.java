package Atributo;

public class Defensivo implements Atributo{
    private int impulsao;
    private int capacidadeDefensiva;

    public Defensivo(int impulsao, int capacidadeDefensiva) {
        this.impulsao = impulsao;
        this.capacidadeDefensiva = capacidadeDefensiva;
    }

    public int getImpulsao() {
        return impulsao;
    }

    public void setImpulsao(int impulsao) {
        this.impulsao = impulsao;
    }

    public int getCapacidadeDefensiva() {
        return capacidadeDefensiva;
    }

    public void setCapacidadeDefensiva(int capacidadeDefensiva) {
        this.capacidadeDefensiva = capacidadeDefensiva;
    }

    @Override
    public double media() {
        return (double) (capacidadeDefensiva + impulsao)/2;
    }
}
