package Atributo;

import java.io.Serializable;

public class Posicionamento implements Atributo, Serializable {
    private static final long serialVersionUID = -6306344867785119697L;
    private int valor;

    public Posicionamento(int valor){
        this.valor = valor;
    }

    public Posicionamento(Posicionamento posicionamento) {
        this.valor = posicionamento.getValor();
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
        return new Posicionamento(this);
    }
}
