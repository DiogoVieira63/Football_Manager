import Atributo.*;

import javax.management.ObjectName;
import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public abstract class Jogador implements Serializable {
    private static final long serialVersionUID = 515493778716284726L;
    private String name;
    private Integer id; //número da camisola
    private Map<Double, List<Atributo>> atributos;
    private List<String> historico;

    //              Constructors                //

    public Jogador(){
        this.name = "";
        this.id = 0;
        this.atributos = new HashMap<>();
        this.historico = new ArrayList<>();
    }

    public Jogador(String name, Integer id, Map<Double, List<Atributo>> atributos,
                   List<String> historico)
    {
        this.name = name;
        this.id = id;
        this.atributos = atributos
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        l -> l.getValue()
                                .stream()
                                .map(Atributo::clone)
                                .collect(Collectors.toList())));
        this.historico = new ArrayList<>(historico);
    }

    public Jogador(Jogador jogador){
        this.name = jogador.getName();
        this.id = jogador.getId();
        this.atributos = jogador.getAtributos();
        this.historico = jogador.getHistorico();
    }

    //              Getters and Setters             //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Map<Double, List<Atributo>> getAtributos() {
        return this.atributos
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        l -> l.getValue()
                                .stream()
                                .map(Atributo::clone)
                                .collect(Collectors.toList())));
    }

    public void setAtributos(Map<Double, List<Atributo>> atributos) {
        this.atributos = atributos
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey,
                        l -> l.getValue()
                                .stream()
                                .map(Atributo::clone)
                                .collect(Collectors.toList())));;
    }

    public List<String> getHistorico() {
        return new ArrayList<>(this.historico);
    }

    public void setHistorico(List<String> historico) {
        this.historico = new ArrayList<>(historico);
    }

    public void addEquipaHistorico (String equipa){
        historico.add(equipa);
    }

    public int habilidadeGeral() {
        double total = 0;
        for (Map.Entry<Double, List<Atributo>> entry : this.atributos.entrySet()){
            Double d = entry.getKey();
            for (Atributo atributo : entry.getValue()){
                total += atributo.valor() * d;
            }
        }
        return (int) (total);
    }

    public void addAtributo(Atributo atributo, Double perc){
        this.atributos.putIfAbsent(perc, new ArrayList<>());
        this.atributos.get(perc).add(atributo);
    }

    //              Abstract Methods                //

    public abstract int habilidadeGeralEspecifica();

    public abstract Jogador clone();

    public List<String> infoJogador (){
        List <String> list = new ArrayList<>();
        list.add("Name:" +name);
        list.add("Número:" +id);
        list.add("Posição:" +this.getClass().getSimpleName());
        list.add("Historico");
        list.addAll(historico);;
        list.add("");
        list.add("Atributos");
        for (List<Atributo> listas:  this.atributos.values())
            for (Atributo atributo : listas)
                list.add(atributo.getClass().getSimpleName() + ":" + atributo.valor());
        return list;
    }

    public static void addToMapa (Atributo atributo,double percentagem, Map<Double,List<Atributo>> mapa){
        mapa.putIfAbsent(percentagem,new ArrayList<>());
        mapa.get(percentagem).add(atributo);
    }


    public static int randomBetween (int low, int high){
        Random r = new Random();
        return r.nextInt(high-low) + low;
    }

    public boolean isLateral (){
        return false;
    }

    public String primeiroUlitmoNome (){
        String[]nomes = name.split(" ");
        return nomes.length == 1 ? nomes[0] : nomes[0] + " " + nomes[nomes.length-1];
    }

    public abstract String getPosition();

    public double percentagemPosicao (String posicao, boolean lateral){
        return this.getClass().getName().equals(posicao) && this.isLateral() == lateral ? 1.0 : 0.75;
    }

    public String toString (){
        return ("(" + getPosition() + ") " + primeiroUlitmoNome());
    }

    public String toString (String pos){
        return ("(" + pos + ") " + primeiroUlitmoNome());
    }


}
