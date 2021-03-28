public class Atributos {
    private int velocidade;
    private int resistencia;
    private int destreza;
    private int impulsao;
    private int jogoDecCabeca;
    private int remate;
    private int capacidadePasse;

    public Atributos (){
        this.velocidade = 70;
        this.resistencia = 70;
        this.destreza = 70;
        this.impulsao = 70;
        this.jogoDecCabeca = 70;
        this.remate = 70;
        this.capacidadePasse = 70;
    }

    public Atributos(int velocidade, int resistencia, int destreza, int impulsao, int jogoDecCabeca, int remate, int capacidadePasse) {
        this.velocidade = velocidade;
        this.resistencia = resistencia;
        this.destreza = destreza;
        this.impulsao = impulsao;
        this.jogoDecCabeca = jogoDecCabeca;
        this.remate = remate;
        this.capacidadePasse = capacidadePasse;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public int getResistencia() {
        return resistencia;
    }

    public void setResistencia(int resistencia) {
        this.resistencia = resistencia;
    }

    public int getDestreza() {
        return destreza;
    }

    public void setDestreza(int destreza) {
        this.destreza = destreza;
    }

    public int getImpulsao() {
        return impulsao;
    }

    public void setImpulsao(int impulsao) {
        this.impulsao = impulsao;
    }

    public int getJogoDecCabeca() {
        return jogoDecCabeca;
    }

    public void setJogoDecCabeca(int jogoDecCabeca) {
        this.jogoDecCabeca = jogoDecCabeca;
    }

    public int getRemate() {
        return remate;
    }

    public void setRemate(int remate) {
        this.remate = remate;
    }

    public int getCapacidadePasse() {
        return capacidadePasse;
    }

    public void setCapacidadePasse(int capacidadePasse) {
        this.capacidadePasse = capacidadePasse;
    }

    @Override
    public String toString() {
        return " velocidade=" + velocidade +
                "\nresistencia=" + resistencia +
                "\n destreza=" + destreza +
                "\n impulsao=" + impulsao +
                "\n jogoDecCabeca=" + jogoDecCabeca +
                "\n remate=" + remate +
                "\n capacidadePasse=" + capacidadePasse;
    }
}
