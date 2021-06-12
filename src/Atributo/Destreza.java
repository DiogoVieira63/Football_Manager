package Atributo;

import java.io.Serializable;

public class Destreza implements Atributo, Serializable {
    private static final long serialVersionUID = 6243549206717881581L;
    private int valor;

    public Destreza(int valor){
        this.valor = valor;
    }

    public Destreza(Destreza destreza){
        this.valor = destreza.getValor();
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
        return new Destreza(this);
    }
}