package Atributo;

public class Atacante implements Atributo{
    private int remate;
    private int jogoDeCabeca;

    public Atacante(int remate, int jogoDeCabeca) {
        this.remate = remate;
        this.jogoDeCabeca = jogoDeCabeca;
    }

    public int getRemate() {
        return remate;
    }

    public void setRemate(int remate) {
        this.remate = remate;
    }

    public int getJogoDeCabeca() {
        return jogoDeCabeca;
    }

    public void setJogoDeCabeca(int jogoDeCabeca) {
        this.jogoDeCabeca = jogoDeCabeca;
    }

    @Override
    public double media() {
        return (double) (remate+ jogoDeCabeca) /2;
    }
}
