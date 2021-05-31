package Atributo;

public class Tecnico implements Atributo{
    private int capacidadeDePasse;
    private int destreza;

    public Tecnico(int capacidadeDePasse, int destreza) {
        this.capacidadeDePasse = capacidadeDePasse;
        this.destreza = destreza;
    }

    public Tecnico(Tecnico tecnico){
        this.capacidadeDePasse = tecnico.getCapacidadeDePasse();
        this.destreza = tecnico.getDestreza();
    }

    public int getCapacidadeDePasse() {
        return capacidadeDePasse;
    }

    public void setCapacidadeDePasse(int capacidadeDePasse) {
        this.capacidadeDePasse = capacidadeDePasse;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public double media() {
        return (double) (destreza+capacidadeDePasse)/2;
    }

    public Atributo clone() {
        return new Tecnico(this);
    }
}
