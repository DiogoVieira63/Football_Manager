import Atributo.Atributo;
import Exceptions.JogadorExistenteException;
import Exceptions.LinhaIncorretaException;

import java.util.*;

public class FootballManager {
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
        controller.carregarLogs();
        boolean stop;
        do {
            stop = controller.run();
        }while (!stop);
    }
}
