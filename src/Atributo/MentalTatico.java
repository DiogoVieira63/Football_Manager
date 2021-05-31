package Atributo;

public class MentalTatico implements Atributo{
    private int posicionamento;
    private int motivacao;

    public MentalTatico(int posicionamento, int motivacao){
        this.posicionamento = posicionamento;
        this.motivacao = motivacao;
    }

    public MentalTatico(MentalTatico mentalTatico){
        this.posicionamento = mentalTatico.getPosicionamento();
        this.motivacao = mentalTatico.getMotivacao();
    }

    public int getPosicionamento() {
        return posicionamento;
    }

    public void setPosicionamento(int posicionamento) {
        this.posicionamento = posicionamento;
    }

    public int getMotivacao() {
        return motivacao;
    }

    public void setMotivacao(int motivacao) {
        this.motivacao = motivacao;
    }

    public double media() {
        return (double) (posicionamento + motivacao)/2;
    }

    public Atributo clone() {
        return new MentalTatico(this);
    }
}
