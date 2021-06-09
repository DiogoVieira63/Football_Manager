package Atributo;

public class Destreza implements Atributo{
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