package Atributo;

public class Tecnico implements Atributo{
    private int capacidadeDePasse;
    private int destreza;

    public Tecnico(int capacidadeDePasse, int destreza) {
        this.capacidadeDePasse = capacidadeDePasse;
        this.destreza = destreza;
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

    @Override
    public double media() {
        return (double) (destreza+capacidadeDePasse)/2;
    }
}
