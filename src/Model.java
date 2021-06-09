import java.io.*;
import java.util.*;

public class Model implements Serializable {
    private static final long serialVersionUID = -6501770757804112150L;
    private CatalogoEquipas catalogoEquipas;
    private List <Jogo> jogos;
    
    public Model (){
        catalogoEquipas = new CatalogoEquipas();
        jogos = new ArrayList<>();
    }

    public List<String> nomesEquipasOrdenados (){
        return catalogoEquipas.nomesOrdenados();
    }

    public List<Object> nomesJogadoresOrdenado (String equipa){
        return catalogoEquipas.nomesOrdenadosEquipa(equipa);
    }


    public void addEquipa (Equipa equipa){
        catalogoEquipas.adicionarEquipa(equipa);
    }

    public int sizeEquipas() {
        return catalogoEquipas.nomesOrdenados().size();
    }

    public Model readFromFile (String filepath) {
        try {
            FileInputStream fileIn = new FileInputStream(filepath);
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Object obj = objectIn.readObject();
            View.printFrase("Ficheiro carregado com sucesso");
            objectIn.close();
            return (Model) obj;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }


    public void writeToFile (String filepath){
        try {
            FileOutputStream fileOut = new FileOutputStream(filepath);
            ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
            objectOut.writeObject(this);
            objectOut.close();
            View.printFrase("Ficheiro guardado com sucesso");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public List<Object> infoJogador (String equipa, int jogador){
        return catalogoEquipas.infoJogador(equipa,jogador);
    }

}
