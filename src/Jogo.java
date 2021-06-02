import java.time.LocalDate;
import java.util.*;

public class Jogo {
    private EquipaJogo casa;
    private Equipa casaEquipa;
    private EquipaJogo fora;
    private Equipa foraEquipa;
    private int golosCasa;
    private int golosFora;
    private LocalDate date;

    //              Constructors                //

    public Jogo(){
        this.casa = new EquipaJogo();
        this.fora = new EquipaJogo();
        this.golosCasa = 0;
        this.golosFora = 0;
        this.date = LocalDate.now();
    }

    public Jogo (EquipaJogo casa, EquipaJogo fora, int golosCasa, int golosFora, LocalDate data){
        this.casa = casa.clone();
        this.fora = fora.clone();
        this.golosCasa = golosCasa;
        this.golosFora = golosFora;
        this.date = data;
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

    public static Jogo parse(String input){
        String[] campos = input.split(",");
        String[] data = campos[4].split("-");

        List<String> jc = new ArrayList<>(); //jogadores casa
        List<String> jf = new ArrayList<>(); //jogadores fora

        Map<String, String> subsC = new HashMap<>(); // substituições casa
        Map<String, String> subsF = new HashMap<>(); // substituições fora

        for (int i = 5; i < 16; i++){
            jc.add(campos[i]);
        }
        for (int i = 16; i < 19; i++){
            String[] sub = campos[i].split("->");
            subsC.put(sub[0], sub[1]);
        }
        for (int i = 19; i < 30; i++){
            jf.add(campos[i]);
        }
        for (int i = 30; i < 33; i++){
            String[] sub = campos[i].split("->");
            subsF.put(sub[0], sub[1]);
        }

        LocalDate dataJogo = LocalDate.of(Integer.parseInt(data[0]), Integer.parseInt(data[1]), Integer.parseInt(data[2]));
        EsquemaTatico esquema = new EsquemaTatico();
        HashSet<String> suplentes = new HashSet<>();
        Map<String, Double> titularesCasa = new HashMap<>();
        Map<String, Double> titularesFora = new HashMap<>();

        for (String id : jc){
            titularesCasa.put(id, 0.0);
        }

        EquipaJogo casa = new EquipaJogo(campos[0], esquema, titularesCasa, suplentes, subsC);

        for (String id : jf){
            titularesFora.put(id, 0.0);
        }

        EquipaJogo fora = new EquipaJogo(campos[0], esquema, titularesFora, suplentes, subsC);

        return new Jogo(casa, fora, 0, 0, dataJogo);
    }

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
