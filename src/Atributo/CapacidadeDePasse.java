package Atributo;

import java.io.Serializable;

public class CapacidadeDePasse implements Atributo, Serializable {
    private static final long serialVersionUID = -1386536411813859607L;
    private int valor;

    public CapacidadeDePasse(int valor){
        this.valor = valor;
    }

    public CapacidadeDePasse(CapacidadeDePasse capacidadeDePasse){
        this.valor = capacidadeDePasse.getValor();
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
        return new CapacidadeDePasse(this);
    }
}