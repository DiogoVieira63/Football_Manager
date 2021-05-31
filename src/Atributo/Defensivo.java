package Atributo;

public class Defensivo implements Atributo{
    private int impulsao;
    private int capacidadeDefensiva;

    public Defensivo(int impulsao, int capacidadeDefensiva) {
        this.impulsao = impulsao;
        this.capacidadeDefensiva = capacidadeDefensiva;
    }

    public Defensivo(Defensivo defensivo){
        this.impulsao = defensivo.getImpulsao();
        this.capacidadeDefensiva = defensivo.getCapacidadeDefensiva();
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

    public double media() {
        return (double) (capacidadeDefensiva + impulsao)/2;
    }

    public Atributo clone() {
        return new Defensivo(this);
    }
}
