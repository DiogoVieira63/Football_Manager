import Atributo.Atributo;
import Exceptions.JogadorExistenteException;
import Exceptions.LinhaIncorretaException;

import java.util.*;

public class FootballManager {
    public static void main(String[] args) {
        Model model = new Model();
        Controller controller = new Controller(model);
        if (controller.carregarLogs()){
            try {
                model = Parser.parse("logs.txt");
                controller.setModel(model);
            }
            catch (JogadorExistenteException | LinhaIncorretaException e) {
                View.printFrase("Erro ao ler o ficheiro logs.txt");
            }
        }
        boolean stop = false;
        do {
            stop = controller.run();
        }while (!stop);
        //System.out.println(model.getEquipaJogo(1).toString());
        //model.efetuarJogo(model.getEquipaJogo(1),model.getEquipaJogo(1));
    }
}
