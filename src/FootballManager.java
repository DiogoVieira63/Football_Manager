import Atributo.Atributo;
import Exceptions.JogadorExistenteException;
import Exceptions.LinhaIncorretaException;

import java.util.*;

public class FootballManager {
    public static void main(String[] args) throws JogadorExistenteException, LinhaIncorretaException {
        Model model = Parser.parse();
        //Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model,view);
        //model.printJogos();
        //View.printPairOrganizedCollection(model.getJogosEquipa("Bach F. C."));
        controller.run();
        //System.out.println(model.getEquipaJogo(1).toString());
        //model.efetuarJogo(model.getEquipaJogo(1),model.getEquipaJogo(1));
    }
}
