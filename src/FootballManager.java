public class FootballManager {
    public static void main(String[] args) {
        GuardaRedes gr = new GuardaRedes();
        Jogador jogador = new Jogador();

        Equipa equipa = new Equipa();
        equipa.listaJogadores.add(gr);
        equipa.listaJogadores.add(jogador);

        System.out.println(equipa.listaJogadores.toString());
    }
}
