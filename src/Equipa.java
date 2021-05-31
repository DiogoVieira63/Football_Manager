import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import Exceptions.*;

public class Equipa {
    private String name;
    private Map<String, Jogador> listaJogadores;
    private boolean lateral;

    //              Constructors                //

    public Equipa() {
        this.name = "";
        this.listaJogadores = new HashMap<>();
        this.lateral = false;
    }

    public Equipa (String name, Map<String, Jogador> listaJogadores, boolean lateral){
        this.name = name;
        this.listaJogadores = listaJogadores
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, j -> j.getValue().clone()));
        this.lateral = lateral;
    }

    public Equipa (Equipa equipa){
        this.name = equipa.getName();
        this.listaJogadores = equipa.getListaJogadores();
        this.lateral = equipa.getLateral();
    }

    public void addJogador (Jogador jogador) throws JogadorExistenteException {
        if (this.listaJogadores.containsKey(jogador.getId()))
            throw new JogadorExistenteException("O Jogador " + jogador.getName() + "já pertence à equipa " + this.name);
        else {
            this.listaJogadores.put(jogador.getId(), jogador.clone());
            jogador.getHistorico().add(this.name);
        }
    }

    public void transferenciaJogador (Jogador jogador, Equipa novaEquipa) throws JogadorExistenteException {
        if (novaEquipa.getListaJogadores().containsKey(jogador.getId()) || jogador.getHistorico().contains(novaEquipa.getName()))
            throw new JogadorExistenteException("O Jogador " + jogador.getName() +"já pertence à equipa " + novaEquipa.getName());
        else{
            this.listaJogadores.remove(jogador.getId());
            novaEquipa.addJogador(jogador.clone());
            jogador.getHistorico().add(novaEquipa.getName());
        }
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

    //              Getters and Setters             //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<String, Jogador> getListaJogadores() {
        return listaJogadores
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, j -> j.getValue().clone()));
    }

    public void setListaJogadores(Map<String, Jogador> listaJogadores) {
        this.listaJogadores = listaJogadores
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, j -> j.getValue().clone()));
    }

    public boolean getLateral() {
        return lateral;
    }

    public void setLateral(boolean lateral) {
        this.lateral = lateral;
    }

}
