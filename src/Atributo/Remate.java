package Atributo;

import java.io.Serializable;

public class Remate implements Atributo, Serializable {
    private static final long serialVersionUID = -1782400894232428529L;
    private int valor;

    public Remate(int valor){
        this.valor = valor;
    }

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
