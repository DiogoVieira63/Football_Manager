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

    public void transferirJogador (String equipa, int jogador, String novaEquipa){
        catalogoEquipas.transferirJogador (equipa,jogador,novaEquipa);
    }

    public CatalogoEquipas getCatalogoEquipas() {
        return catalogoEquipas;
    }

    public void addJogo (Jogo jogo){
        jogos.add(jogo);
    }

    public void printJogos (){
        for (Jogo jogo : jogos){
            System.out.println(jogo.getCasa().getIdEquipa() + "-> " + jogo.getGolosCasa());
            System.out.println(jogo.getFora().getIdEquipa() + "-> " + jogo.getGolosFora());
            System.out.println();
        }
    }

    public List<Object> getJogosEquipa (String equipa){
        List<Object> list = new ArrayList<>();
        for (Jogo jogo : jogos){
            String nameCasa = jogo.getCasa().getIdEquipa();
            String nameFora = jogo.getFora().getIdEquipa();
            if (nameCasa.equals(equipa) || nameFora.equals(equipa)){
                list.add(Map.entry(nameCasa +  " vs ", nameFora));
                list.add(Map.entry(jogo.getGolosCasa() + " - ",jogo.getGolosFora()));
            }
        }
        return list;
    }

    public EquipaJogo getEquipaJogo (int i){
        return jogos.get(i).getCasa();
    }

    public Set<Integer> getNumeros (String equipa){
        return catalogoEquipas.getNumeros (equipa);
    }

    public void efetuarJogo (EquipaJogo casa, EquipaJogo fora){
        Jogo jogo = new Jogo(casa,fora);
        String nomeCasa = casa.getIdEquipa();
        String nomeFora = casa.getIdEquipa();
        Map<Integer,Integer> overallCasa = catalogoEquipas.overallJogadores (nomeCasa);
        Map<Integer,Integer> overallFora = catalogoEquipas.overallJogadores(nomeFora);
        jogo.calculaJogo(overallCasa,overallFora);
    }

    public List<Object> possiveisPosicao (String equipa,String posicao, boolean lateral){
        return catalogoEquipas.possiveisPosicao(equipa,posicao,lateral);
    }

}
