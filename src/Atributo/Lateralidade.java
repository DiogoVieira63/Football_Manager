package Atributo;

public class Lateralidade implements Atributo{
    private int cruzamento;

    public Lateralidade(int cruzamento){
        this.cruzamento = cruzamento;
    }

    public Lateralidade(Lateralidade lateralidade){
        this.cruzamento = lateralidade.getCruzamento();
    }

    public int getCruzamento() {
        return cruzamento;
    }

    public void setCruzamento(int cruzamento) {
        this.cruzamento = cruzamento;
    }

    public double media() {
        return this.cruzamento;
    }

    public Atributo clone() {
        return new Lateralidade(this);
    }
}
