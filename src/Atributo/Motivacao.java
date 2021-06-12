package Atributo;

import java.io.Serializable;

public class Motivacao implements Atributo, Serializable {
    private static final long serialVersionUID = -5990072298100189148L;
    private int valor;

    public Motivacao(int valor){
        this.valor = valor;
    }

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
