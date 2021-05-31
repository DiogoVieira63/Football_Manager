import java.util.*;

public class EquipaJogo {
    private String idEquipa;
    private EsquemaTatico esquemaTatico;
    private Map<String, Double> titulares;
    private HashSet<String> suplentes;
    private Map<String, String> substituições;

    //              Constructors                //

    public EquipaJogo(){
        this.idEquipa = "";
        this.titulares = new HashMap<>();
        this.suplentes = new HashSet<>();
        this.substituições = new HashMap<>();
    }

    public EquipaJogo(String idEquipa, Map<String, Double> titulares, HashSet<String> suplentes,
                      Map<String, String> substituições){
        this.idEquipa = idEquipa;
        this.titulares = new HashMap<String, Double>(titulares);
        this.suplentes = new HashSet<String>(suplentes);
        this.substituições = new HashMap<String, String>(substituições);
    }

    public EquipaJogo(EquipaJogo equipa){
        this.idEquipa = equipa.getIdEquipa();
        this.titulares = equipa.getTitulares();
        this.suplentes = equipa.getSuplentes();
        this.substituições = equipa.getSubstituições();
    }

    //              Getters and Setters             //

    public String getIdEquipa() {
        return idEquipa;
    }

    public void setIdEquipa(String idEquipa) {
        this.idEquipa = idEquipa;
    }

    public Map<String, Double> getTitulares() {
        return new HashMap<String, Double>(this.titulares);
    }

    public void setTitulares(Map<String, Double> titulares) {
        this.titulares = new HashMap<String, Double>(titulares);
    }

    public HashSet<String> getSuplentes() {
        return new HashSet<String>(this.suplentes);
    }

    public void setSuplentes(HashSet<String> suplentes) {
        this.suplentes = new HashSet<String>(suplentes);
    }

    public Map<String, String> getSubstituições() {
        return new HashMap<String, String>(this.substituições);
    }

    public void setSubstituições(Map<String, String> substituições) {
        this.substituições = new HashMap<String, String>(substituições);
    }

    public EquipaJogo clone(){
        return new EquipaJogo(this);
    }
}
