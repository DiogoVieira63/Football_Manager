import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    /*

    public void addJogador (Jogador jogador) {
        Jogador clone = jogador.clone();
        clone.addEquipaHistorico(getName());
        if (getListaJogadores().stream().noneMatch(clone::equals)){
            listaJogadores.add(jogador);
            jogador.addEquipaHistorico(getName());
        }
    }

    public void transferenciaJogador (Jogador jogador, Equipa novaEquipa){
        this.getListaJogadores().remove(jogador);
        novaEquipa.addJogador(jogador);
    }

    /**
     * Returns a list with Jogadores from that Position
     * @param position The Position
     * @return The List


    public List<Jogador> getPosition (Position position){
        return getListaJogadores().stream().
                filter(jogador -> jogador.getPosition().equals(position))
                .collect(Collectors.toList());
    }

    /**
     * Imprime o nome do jogador e a sua habilidade por ordem de posições
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

    public int habilidadeEquipa (){
        int total = 0;
        for (Jogador jogador : getListaJogadores()){
            total += jogador.habilidadeGeral();
        }
        return total/getListaJogadores().size();
    }
     */
}
