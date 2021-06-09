package Atributo;

public class Resistencia implements Atributo{
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