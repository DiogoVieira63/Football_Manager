import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Equipa {
    private String name;
    private List<Jogador> listaJogadores;
    private int positionLeague;

    public Equipa (){
        this.listaJogadores = new ArrayList<>();
    }

    public Equipa (String name){
        this.name = name;
        this.listaJogadores = new ArrayList<>();
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns a list with Jogadores from that Position
     * @param position The Position
     * @return The List
     */

    public List<Jogador> getPosition (Position position){
        return getListaJogadores().stream().
                filter(jogador -> jogador.getPosition().equals(position))
                .collect(Collectors.toList());
    }

    /**
     * Imprime o nome do jogador e a sua habilidade por ordem de posições
     */
    public void printHabilidadeEquipa () {
        System.out.format("%-15s%-4s\n",getName(),habilidadeEquipa());
        System.out.println("---------------------------------------------");
        System.out.format("%-14s%-20s%-4s\n","Posição","Nome","Rating");
        System.out.println("---------------------------------------------");
        for (int i = 0; i < 5; i++) {
            for (Jogador jogador : getPosition(Position.getPlayer(i)))
            System.out.format("%-14s%-20s%-4s\n", jogador.getPosition(), jogador.getName(),jogador.habilidadeGeral());
        }
    }

    public double habilidadeEquipa (){
        int total = 0;
        for (Jogador jogador : getListaJogadores()){
            total += jogador.habilidadeGeral();
        }
        return (double) total/getListaJogadores().size();
    }

    public List<Jogador> getListaJogadores() {
        return listaJogadores;
    }

    public void setListaJogadores(List<Jogador> listaJogadores) {
        this.listaJogadores.addAll(listaJogadores);
    }

    public int getPositionLeague() {
        return positionLeague;
    }

    public void setPositionLeague(int positionLeague) {
        this.positionLeague = positionLeague;
    }

    @Override
    public String toString() {
        return "Equipa{" +
                "name='" + name + '\'' +
                ", listaJogadores=" + listaJogadores +
                ", positionLeague=" + positionLeague +
                '}';
    }
}
