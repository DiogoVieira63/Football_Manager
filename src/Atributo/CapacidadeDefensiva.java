package Atributo;

public class CapacidadeDefensiva implements Atributo{
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
