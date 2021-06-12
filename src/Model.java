import Exceptions.EquipaJaExisteException;
import Exceptions.EquipaSemJogadoresException;
import Exceptions.JogadorExistenteException;
import Exceptions.NaoHaJogadorPosicaoException;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

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

    public List<Object> nomesJogadoresOrdenado (String equipa)throws EquipaSemJogadoresException {
        return catalogoEquipas.nomesOrdenadosEquipa(equipa);
    }


    public void addEquipa (Equipa equipa) throws EquipaJaExisteException {
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

    public List<String> infoJogador (String equipa, int jogador){
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


    public List<String> getJogosEquipa (String equipa){
        List<String> list = new ArrayList<>();
        for (Jogo jogo : jogos){
            String nameCasa = jogo.getCasa().getIdEquipa();
            String nameFora = jogo.getFora().getIdEquipa();
            if (nameCasa.equals(equipa) || nameFora.equals(equipa)){
                list.add(nameCasa +  " vs " + nameFora);
                list.add(jogo.getGolosCasa() + " - " + jogo.getGolosFora());
            }
        }
        return list;
    }


    public Set<Integer> getNumeros (String equipa){
        return catalogoEquipas.getNumeros (equipa);
    }

    public String efetuarJogo (EquipaJogo casa, EquipaJogo fora){
        Jogo jogo = new Jogo(casa,fora);
        String nomeCasa = casa.getIdEquipa();
        String nomeFora = fora.getIdEquipa();
        Map<Integer,Integer> overallCasa = catalogoEquipas.overallJogadores(casa);
        Map<Integer,Integer> overallFora = catalogoEquipas.overallJogadores(fora);
        jogo.calculaJogo(overallCasa,overallFora);
        addJogo(jogo);
        return casa.getIdEquipa() + " " + jogo.getGolosCasa() + " - " + jogo.getGolosFora() + " " + fora.getIdEquipa();
    }

    public List<Object> possiveisPosicao (String equipa,String posicao, boolean lateral,List<Integer> usados)throws NaoHaJogadorPosicaoException {
        return catalogoEquipas.possiveisPosicao(equipa,posicao,lateral,usados);
    }

    public int OverallTitulares (EquipaJogo equipaJogo){
        return catalogoEquipas.OverallTitulares (equipaJogo.getIdEquipa(),equipaJogo.getTitulares(), equipaJogo.getEsquemaTatico());
    }

    public List<String> ListJogadores(String equipa, Collection<Integer> collection){
        return catalogoEquipas.listJogadores(equipa,collection);
    }

    public boolean isEmptyEquipa (String equipa){
        return catalogoEquipas.isEmptyEquipa (equipa);
    }

    public void addJogador(Jogador jogador, String equipa) throws JogadorExistenteException {
        catalogoEquipas.addJogador(jogador, equipa);
    }

    public boolean containsEquipa(String nomeEquipa) {
        return catalogoEquipas.containsEquipa (nomeEquipa);
    }

    public Jogador doJogador (int jogador, String line, boolean lateral){
        switch (jogador){
            case 0:
                return GuardaRedes.parseControlador(line);
            case 1:
                return Defesa.parseControlador(line,lateral);
            case 2:
                return Medio.parseControlador(line,lateral);
            case 3:
                return Avancado.parseControlador(line,lateral);
        }
        return null;
    }

}
