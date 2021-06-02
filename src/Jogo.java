public class Jogo {
    private EquipaJogo casa;
    private Equipa casaEquipa;
    private EquipaJogo fora;
    private Equipa foraEquipa;
    private int golosCasa;
    private int golosFora;

    //              Constructors                //

    public Jogo(){
        this.casa = new EquipaJogo();
        this.fora = new EquipaJogo();
        this.golosCasa = 0;
        this.golosFora = 0;
    }

    public Jogo (EquipaJogo casa, EquipaJogo fora, int golosCasa, int golosFora){
        this.casa = casa.clone();
        this.fora = fora.clone();
        this.golosCasa = golosCasa;
        this.golosFora = golosFora;
    }

    public Jogo(Jogo jogo){
        this.casa = jogo.getCasa();
        this.fora = jogo.getFora();
        this.golosCasa = jogo.getGolosCasa();
        this.golosFora = jogo.getGolosFora();
    }

    public void goloCasa(){
        setGolosCasa(this.golosCasa + 1);
    }

    public void goloFora(){
        setGolosFora(this.golosFora + 1);
    }

    /*
    public String vencedor(){

    }

     */

    //              Getters and Setters             //

    public EquipaJogo getCasa() {
        return casa;
    }

    public void setCasa(EquipaJogo casa) {
        this.casa = casa.clone();
    }

    public EquipaJogo getFora() {
        return this.fora;
    }

    public void setFora(EquipaJogo fora) {
        this.fora = fora.clone();
    }

    public int getGolosCasa() {
        return golosCasa;
    }

    public void setGolosCasa(int golosCasa) {
        this.golosCasa = golosCasa;
    }

    public int getGolosFora() {
        return golosFora;
    }

    public void setGolosFora(int golosFora) {
        this.golosFora = golosFora;
    }
}
