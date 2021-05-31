package Atributo;

public class Atacante implements Atributo{
    private int remate;
    private int jogoDeCabeca;

    public Atacante(int remate, int jogoDeCabeca) {
        this.remate = remate;
        this.jogoDeCabeca = jogoDeCabeca;
    }

    public Atacante(Atacante atacante){
        this.remate = atacante.getRemate();
        this.jogoDeCabeca = atacante.getJogoDeCabeca();
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

    public double media() {
        return (double) (remate+ jogoDeCabeca) /2;
    }

    public Atributo clone() {
        return new Atacante(this);
    }
}
