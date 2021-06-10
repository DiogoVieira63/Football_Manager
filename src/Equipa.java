import java.io.Serializable;
import java.sql.ClientInfoStatus;
import java.util.*;
import java.util.stream.Collectors;
import Exceptions.*;

public class Equipa implements Serializable {
    private static final long serialVersionUID = -5626352478359403106L;
    private String name;
    private Map<Integer, Jogador> listaJogadores;

    //              Constructors                //

    public Equipa() {
        this.name = "";
        this.listaJogadores = new HashMap<Integer, Jogador>();
    }

    public Equipa(String name){
        this.name = name;
        this.listaJogadores = new HashMap<Integer, Jogador>();
    }

    public Equipa (String name, Map<Integer, Jogador> listaJogadores, boolean lateral){
        this.name = name;
        this.listaJogadores = listaJogadores
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, j -> j.getValue().clone()));
    }

    public Equipa (Equipa equipa){
        this.name = equipa.getName();
        this.listaJogadores = equipa.getListaJogadores();
    }

    public void addJogador (Jogador jogador) throws JogadorExistenteException {
        if (this.listaJogadores.containsKey(jogador.getId()))
            throw new JogadorExistenteException("O Jogador " + jogador.getName() + "já pertence à equipa " + this.name);
        else {
            this.listaJogadores.put(jogador.getId(), jogador);
            jogador.addEquipaHistorico(this.name);
        }
    }

    public void transferenciaJogador (int Idjogador, Equipa novaEquipa) throws JogadorExistenteException {
        Jogador jogador = listaJogadores.get(Idjogador);
        int size = jogador.getHistorico().size();
        if (jogador.getHistorico().get(size-1).equals(novaEquipa.name))
            throw new JogadorExistenteException("O Jogador " + jogador.getName() +"já pertence à equipa " + novaEquipa.getName());
        else{
            this.listaJogadores.remove(jogador.getId());
            int number = jogador.getId();
            while (novaEquipa.containsId(number)){
                number = Jogador.randomBetween(0,100);
            }
            jogador.setId(number);
            novaEquipa.addJogador(jogador);
        }
    }

    public double mediaGeralEquipa (Equipa equipa){
        double total=0;
        for (Jogador player : this.listaJogadores.values()){
            total += player.habilidadeGeralEspecifica();
        }
        return total/this.listaJogadores.size();
    }

    public Jogador getJogador(Integer idJog){
        return this.listaJogadores.get(idJog).clone();
    }

    /*

    public List<Jogador> getPosition (Position position){
        return getListaJogadores().stream().
                filter(jogador -> jogador.getPosition().equals(position))
                .collect(Collectors.toList());
    }



    //Imprime o nome do jogador e a sua habilidade por ordem de posições

    public void printHabilidadeEquipa () {
        System.out.format("%-15s%-4s\n",getName(),habilidadeEquipa());
        System.out.println("---------------------------------------------");
        System.out.format("%-14s%-20s%-4s\n","Posição","Nome","Rating");
        System.out.println("---------------------------------------------");
        for (int i = 0; i < 4; i++) {
            for (Jogador jogador : getPosition(Position.getPlayer(i)))
            System.out.format("%-14s%-20s%-4s\n", jogador.getPosition(), jogador.getName(),jogador.habilidadeGeral());
        }
        System.out.println("---------------------------------------------");
    }
    */

    public int habilidadeEquipa (){
        int total = 0;
        for (Jogador jogador : this.listaJogadores.values()){
            total += jogador.habilidadeGeralEspecifica();
        }
        return total/getListaJogadores().size();
    }

    public static Equipa parse(String input){
        String[] campos = input.split(",");
        return new Equipa(campos[0]);
    }

    //              Getters and Setters             //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Jogador> getListaJogadores() {
        return listaJogadores
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, j -> j.getValue().clone()));
    }


    public void setListaJogadores(Map<Integer, Jogador> listaJogadores) {
        this.listaJogadores = listaJogadores
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, j -> j.getValue().clone()));
    }


    public List<Object> organizadoNumero (){
        Set<Integer> set = new TreeSet<>(listaJogadores.keySet());
        List <Object> list = new ArrayList<>();
        for (Integer number : set){
            String name = listaJogadores.get(number).getName();
            list.add(Map.entry(number,name));
        }
        list.add(Map.entry("",""));
        list.add(Map.entry("Overall Equipa:",this.habilidadeEquipa()));
        return list;
    }

    public List<Object> infoJogador (int number){
        return listaJogadores.get(number).infoJogador();
    }

    public Equipa clone(){
        return new Equipa(this);
    }

    public Set<Integer> getNumeros (){
        return listaJogadores.keySet();
    }


    public boolean containsId (int id){
        return listaJogadores.containsKey(id);
    }

    public Map<Integer,Integer> mapOverall (){
        Map<Integer,Integer> map = new HashMap<>();
        for (Jogador jogador : listaJogadores.values()){
            map.put(jogador.getId(), jogador.habilidadeGeralEspecifica());
        }
        return map;
    }


    public List<Object> possiveisPosicao (String posicao,boolean lateral){
        List<Object> list = new ArrayList<>();
        for (Jogador jogador : listaJogadores.values()){
            if (jogador.getClass().getName().equals(posicao) || (!posicao.equals("GuardaRedes") && lateral == jogador.isLateral())){
                list.add(Map.entry(jogador.getId() + " - " + jogador.getName() +  " - ",jogador.habilidadeGeralEspecifica()));
            }
        }
        return list;
    }

}
