package Atributo;

public class Motivacao implements Atributo{
    private int valor;

    public Motivacao(Motivacao motivacao){
        this.valor = motivacao.getValor();
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public int valor() {
        return this.valor;
    }

    public Atributo clone() {
        return new Motivacao(this);
    }
}
