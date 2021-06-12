package Atributo;

import java.io.Serializable;

public class Velocidade implements Atributo, Serializable {
    private static final long serialVersionUID = -4906235120994484801L;
    private int valor;

    public Velocidade(int valor){
        this.valor = valor;
    }

    public Velocidade(Velocidade velocidade){
        this.valor = velocidade.getValor();
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
        return new Velocidade(this);
    }
}
