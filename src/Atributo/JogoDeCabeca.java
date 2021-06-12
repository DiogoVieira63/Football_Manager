package Atributo;

public class JogoDeCabeca implements Atributo{
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
