package Atributo;

import java.io.Serializable;

public class Impulsao implements Atributo, Serializable {
    private static final long serialVersionUID = 1458962975365698809L;
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
