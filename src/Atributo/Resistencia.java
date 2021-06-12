package Atributo;

import java.io.Serializable;

public class Resistencia implements Atributo, Serializable {
    private static final long serialVersionUID = 7188897572938923615L;
    private int valor;

    public Resistencia(int valor){
        this.valor = valor;
    }

    public Resistencia(Resistencia resistencia){
        this.valor = resistencia.getValor();
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
        return new Resistencia(this);
    }
}