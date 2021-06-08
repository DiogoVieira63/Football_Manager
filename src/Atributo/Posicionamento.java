package Atributo;

public class Posicionamento implements Atributo {
    private int valor;

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
