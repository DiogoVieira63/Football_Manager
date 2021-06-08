package Atributo;

public class CapacidadeDePasse implements Atributo{
    private int valor;

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