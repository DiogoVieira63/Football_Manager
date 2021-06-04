import Atributo.Atributo;
import Exceptions.JogadorExistenteException;
import Exceptions.LinhaIncorretaException;

import java.util.AbstractMap;

public class FootballManager {
    public static void main(String[] args) throws JogadorExistenteException, LinhaIncorretaException {
        /*
        GuardaRedes gr = new GuardaRedes();
        Jogador defesa = new Defesa();
        Jogador avancado = new Avancado();
        gr.setName("Oblak");
        defesa.setName("Sergio Ramos");
        avancado.setName("Messi");
         */
        Parser.parse();
    }
}
