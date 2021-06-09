import Exceptions.SubstituicaoException;

import java.io.Serializable;
import java.util.*;

public class EquipaJogo implements Serializable {
    private static final long serialVersionUID = 3167795678329552037L;
    private String idEquipa;
    private EsquemaTatico esquemaTatico;
    private Map<Integer, Double> titulares;
    private HashSet<Integer> suplentes;
    private Map<Integer, Integer> substituições;


    //              Constructors                //

    public EquipaJogo(){
        this.idEquipa = "";
        this.esquemaTatico = new EsquemaTatico();
        this.titulares = new HashMap<>();
        this.suplentes = new HashSet<>();
        this.substituições = new HashMap<>();
    }

    public EquipaJogo(EquipaJogo equipa){
        this.idEquipa = equipa.getIdEquipa();
        this.titulares = equipa.getTitulares();
        this.suplentes = equipa.getSuplentes();
        this.substituições = equipa.getSubstituições();
    }

    public void substituicao(Integer n1, Integer n2) throws SubstituicaoException{
        // n1 é o que sai, n2 entra
        if (!(this.titulares.containsKey(n1)) || !(this.suplentes.contains(n2))){
            throw new SubstituicaoException("Não é possível realizar esta substituição");
        }
        else {
            this.titulares.remove(n1);
            this.suplentes.remove(n2);
            this.titulares.put(n2,0.0);
            this.substituições.put(n1,n2);
        }
    }

    public double mediaGeralTitulares (Equipa equipa){
        double total=0;
        for (Integer number : this.titulares.keySet()){
            total += equipa.getJogador(number).habilidadeGeralEspecifica();
        }
        return total/this.titulares.size();
    }

    //              Getters and Setters             //

    public String getIdEquipa() {
        return idEquipa;
    }

    public void setIdEquipa(String idEquipa) {
        this.idEquipa = idEquipa;
    }

    public Map<Integer, Double> getTitulares() {
        return new HashMap<Integer, Double>(this.titulares);
    }

    public void setTitulares(Map<Integer, Double> titulares) {
        this.titulares = new HashMap<Integer, Double>(titulares);
    }

    public HashSet<Integer> getSuplentes() {
        return new HashSet<Integer>(this.suplentes);
    }

    public void setSuplentes(HashSet<Integer> suplentes) {
        this.suplentes = new HashSet<Integer>(suplentes);
    }

    public Map<Integer,Integer> getSubstituições() {
        return new HashMap<Integer, Integer>(this.substituições);
    }

    public void setSubstituições(Map<Integer, Integer> substituições) {
        this.substituições = new HashMap<Integer, Integer>(substituições);
    }

    public EquipaJogo clone(){
        return new EquipaJogo(this);
    }
}
