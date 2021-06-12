package Atributo;

import java.io.Serializable;

public class JogoDeCabeca implements Atributo, Serializable {
    private static final long serialVersionUID = -472912748115978338L;
    private int valor;

    public JogoDeCabeca(int valor){
        this.valor = valor;
    }

    public JogoDeCabeca(JogoDeCabeca jogoDeCabeca){
        this.valor = jogoDeCabeca.getValor();
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
        return new JogoDeCabeca(this);
    }
}
