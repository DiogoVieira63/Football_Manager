package Atributo;

import java.io.Serializable;

public class Cruzamento implements Atributo, Serializable {
    private static final long serialVersionUID = 7927435869866131145L;
    private int valor;

    public Cruzamento(int valor){
        this.valor = valor;
    }

    public Cruzamento(Cruzamento cruzamento){
        this.valor = cruzamento.getValor();
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
        return new Cruzamento(this);
    }
}
