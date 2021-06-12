import Exceptions.SubstituicaoException;

import java.io.Serializable;
import java.util.*;

public class EquipaJogo implements Serializable {
    private static final long serialVersionUID = 3167795678329552037L;
    private String idEquipa;
    private EsquemaTatico esquemaTatico;
    private List<Integer> titulares;
    private Set<Integer> suplentes;
    private List<Substituicao> substituições;


    //              Constructors                //

    public EquipaJogo (){
        this.idEquipa = "";
        this.esquemaTatico = new EsquemaTatico();
        this.titulares = new ArrayList<>();
        this.suplentes = new HashSet<>();
        this.substituições = new ArrayList<>();
    }

    public EquipaJogo(String idEquipa,Set<Integer> set){
        this.idEquipa = idEquipa;
        this.esquemaTatico = new EsquemaTatico();
        this.titulares = new ArrayList<>();
        this.suplentes = new HashSet<>(set);
        this.substituições = new ArrayList<>();
    }

    public EquipaJogo(EquipaJogo equipa){
        this.idEquipa = equipa.getIdEquipa();
        this.titulares = equipa.getTitulares();
        this.suplentes = equipa.getSuplentes();
        this.substituições = equipa.getSubstituições();
    }

    public void substituicao(Integer n1, Integer n2) throws SubstituicaoException{
        // n1 é o que sai, n2 entra
        if (!(this.titulares.contains(n1)) || !(this.suplentes.contains(n2))){
            throw new SubstituicaoException("Não é possível realizar esta substituição");
        }
        else {
            this.suplentes.remove(n2);
            int index = this.titulares.indexOf(n1);
            this.titulares.set(index,n2);
        }
    }


    public double mediaGeralTitulares (Equipa equipa){
        double total=0;
        for (Integer number : this.titulares){
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

    public List <Integer> getTitulares() {
        return new ArrayList<>(this.titulares);
    }

    public void setTitulares(List<Integer> titulares) {
        this.titulares = new ArrayList<>(titulares);
    }

    public Set<Integer> getSuplentes() {
        return new HashSet<Integer>(this.suplentes);
    }

    public void setSuplentes(Set<Integer> suplentes) {
        this.suplentes = new HashSet<Integer>(suplentes);
    }

    public void setEsquemaTatico(EsquemaTatico esquemaTatico) {
        this.esquemaTatico = esquemaTatico;
    }

    public EsquemaTatico getEsquemaTatico() {
        return esquemaTatico;
    }

    public List<Substituicao> getSubstituições() {
        return new ArrayList<>(this.substituições);
    }

    public void setSubstituições(List <Substituicao> substituições) {
        this.substituições = new ArrayList<>(substituições);
    }

    public void addSubstituicao (Integer out, Integer in){
        substituições.add(new Substituicao(out,in));
    }

    public void addTitular (int id){
        suplentes.remove(id);
        titulares.add(id);
    }

    public boolean isTitular (int id){
        return titulares.contains(id);
    }

    public boolean isSuplente (int id){
        return suplentes.contains(id);
    }

    public List<Integer> getDefesas (){
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i <= esquemaTatico.getDefesas(); i++){
            list.add(titulares.get(i));
        }
        return list;
    }

    public List<Integer> getMedios (){
        List<Integer> list = new ArrayList<>();
        int defesas = esquemaTatico.getDefesas();
        int medios = esquemaTatico.getMedios();
        for (int i = defesas + 1; i <= defesas+medios; i++){
            list.add(titulares.get(i));
        }
        return list;
    }

    public List<Integer> getAvancados (){
        List<Integer> list = new ArrayList<>();
        int defesas = esquemaTatico.getDefesas();
        int medios = esquemaTatico.getMedios();
        for (int i = defesas + medios + 1; i < 11;i++){
            list.add(titulares.get(i));
        }
        return list;
    }

    public EquipaJogo clone(){
        return new EquipaJogo(this);
    }

    public List<String> posicoes (){
        return esquemaTatico.posicoes();
    }

    public Map.Entry<String,Boolean> infoPosicao (int number){
        return esquemaTatico.infoPosicao(number);
    }

    @Override
    public String toString() {
        return "EquipaJogo{" +
                "idEquipa='" + idEquipa + '\'' +
                ", esquemaTatico=" + esquemaTatico +
                ", titulares=" + titulares +
                ", suplentes=" + suplentes +
                ", substituições=" + substituições +
                '}';
    }

    public boolean inSubstituicao (int number){
        for (Substituicao sub : substituições){
            if (sub.getIn() == number || sub.getOut() == number) return  true;
        }
        return false;
    }


    public void fazeSubstituicoes (){
        for (Substituicao sub : substituições){
            try {
                substituicao(sub.getOut(),sub.getIn());
                sub.setDone(true);
            } catch (SubstituicaoException e) {
                e.printStackTrace();
            }
        }
    }


    public boolean haSubstituicoes (){
        return substituições.size() != 0;
    }

    public String getSubString (int number){
        if (number < substituições.size()){
            Substituicao sub = substituições.get(number);
            if (sub.getDone()) return sub.getOut() + "->" + sub.getIn();
        }
        return "      ";
    }
}
