package Atributo;

public class Remate implements Atributo{
    private int valor;

    public Remate(Remate remate){
        this.valor = remate.getValor();
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
        return new Remate(this);
    }
}
