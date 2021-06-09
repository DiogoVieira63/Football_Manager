import Atributo.Atributo;
import Exceptions.JogadorExistenteException;
import Exceptions.LinhaIncorretaException;

import java.util.AbstractMap;
import java.util.Scanner;

public class FootballManager {
    public static void main(String[] args) throws JogadorExistenteException, LinhaIncorretaException {
        Model model = Parser.parse();
        //Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model,view);
        controller.run();
    }
}
