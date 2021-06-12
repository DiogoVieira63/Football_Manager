package Atributo;

public class Impulsao implements Atributo{
    private int valor;

    public Impulsao(int valor){
        this.valor = valor;
    }

    public Impulsao(Impulsao impulsao){
        this.valor = impulsao.getValor();
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
        return new Impulsao(this);
    }
}
