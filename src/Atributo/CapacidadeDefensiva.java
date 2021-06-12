package Atributo;

import java.io.Serializable;

public class CapacidadeDefensiva implements Atributo, Serializable {
    private static final long serialVersionUID = 1200759775244012189L;
    private int valor;
    public CapacidadeDefensiva(int valor){
        this.valor = valor;
    }

    public CapacidadeDefensiva(CapacidadeDefensiva capacidadeDefensiva){
        this.valor = capacidadeDefensiva.getValor();
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
        return new CapacidadeDefensiva(this);
    }
}
