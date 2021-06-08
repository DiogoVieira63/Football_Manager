package Atributo;

public class Cruzamento implements Atributo{
    private int valor;

    public Cruzamento(Cruzamento cruzamento){
        this.valor = cruzamento.getValor();
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
        return new Cruzamento(this);
    }
}
