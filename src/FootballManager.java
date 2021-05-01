import java.util.ArrayList;
import java.util.List;

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
        Atributos atributos = new Atributos(90,70,90,70,80,90,80,40);
        jogador.setAtributos(atributos);
        avancado.setAtributos(atributos);
        defesa.setAtributos(atributos);
        gr.setAtributos(atributos);
        gr.setElasticidade(90);
    }
}
