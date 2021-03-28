public class FootballManager {
    public static void main(String[] args) {
        GuardaRedes gr = new GuardaRedes();
        Jogador jogador = new Jogador();
        Jogador defesa = new Jogador();
        Jogador avancado = new Jogador();
        defesa.setPosition(Position.DEFESA);
        avancado.setPosition(Position.AVANCADO);
        gr.setName("Oblak");
        defesa.setName("Sergio Ramos");
        jogador.setName("DeBruyne");
        avancado.setName("Messi");

        Equipa braga = new Equipa("Braga");
        Equipa benfica = new Equipa("Benfica");
        League liga = new League();
        liga.addEquipa(braga);
        liga.addEquipa(benfica);
        System.out.println(liga.getTabela().toString());


        braga.addJogador(jogador);
        braga.transferenciaJogador(jogador,benfica);
        benfica.addJogador(defesa);
        benfica.addJogador(avancado);
        benfica.addJogador(gr);
        Atributos atributos = new Atributos(80,80,80,80,80,80,80);
        jogador.setAtributos(atributos);

        benfica.printHabilidadeEquipa();

/*        System.out.println(braga.getListaJogadores());
        System.out.println("----------------------------------------");
        System.out.println(benfica.getPosition(Position.GUARDAREDES));*/
    }
}
