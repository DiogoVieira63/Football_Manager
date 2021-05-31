import Atributo.Atributo;

import java.util.AbstractMap;

public class FootballManager {
    public static void main(String[] args) {

        GuardaRedes gr = new GuardaRedes();
        Jogador defesa = new Defesa();
        Jogador avancado = new Avancado();
        gr.setName("Oblak");
        defesa.setName("Sergio Ramos");
        avancado.setName("Messi");
    }
}
