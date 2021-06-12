import Exceptions.EquipaJaExisteException;
import Exceptions.EquipaSemJogadoresException;
import Exceptions.JogadorExistenteException;
import Exceptions.NaoHaJogadorPosicaoException;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

public class CatalogoEquipas implements Serializable {
    private static final long serialVersionUID = 5384040823095036905L;
    private Map<String, Equipa> catalogoEquipas;

    //              Constructors                //

    public CatalogoEquipas(){
        this.catalogoEquipas = new HashMap<>();
    }

    public CatalogoEquipas (Map<String, Equipa> catalogoEquipas){
        this.catalogoEquipas = catalogoEquipas
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
    }

    public CatalogoEquipas (CatalogoEquipas catalogo){
        this.catalogoEquipas = catalogo.getCatalogoEquipas();
    }

    //              Getters and Setters             //

    public Map<String, Equipa> getCatalogoEquipas() {
        return this.catalogoEquipas
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
    }

    public void setCatalogoEquipas(Map<String, Equipa> catalogoEquipas) {
        this.catalogoEquipas = catalogoEquipas
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
    }

    public void adicionarEquipa (Equipa equipa) throws EquipaJaExisteException {
        if (catalogoEquipas.containsKey(equipa.getName())) throw new EquipaJaExisteException ("A Equipa " + equipa.getName() +  " já existe");
        catalogoEquipas.put(equipa.getName(),equipa);
    }

    public List<String> nomesOrdenados (){
        Set <String> set = new TreeSet<>(catalogoEquipas.keySet());
        return new ArrayList<String>(set);
    }


    public List<Object> nomesOrdenadosEquipa (String equipa)throws EquipaSemJogadoresException {
        return catalogoEquipas.get(equipa).organizadoNumero();
    }

    public List<String> infoJogador (String equipa, int jogador){
        return catalogoEquipas.get(equipa).infoJogador(jogador);
    }

    public void transferirJogador(String equipa, int jogador, String novaEquipa) {
        try {
            catalogoEquipas.get(equipa).transferenciaJogador(jogador,catalogoEquipas.get(novaEquipa));
        } catch (JogadorExistenteException e) {
            e.printStackTrace();
        }
    }


    public Equipa getEquipa (String equipa){
        return catalogoEquipas.get(equipa).clone();
    }

    public Map<Integer, Integer> overallJogadores(EquipaJogo equipa) {
        return catalogoEquipas.get(equipa.getIdEquipa()).mapOverall(equipa);
    }

    public Set<Integer> getNumeros(String equipa) {
        return catalogoEquipas.get(equipa).getNumeros();
    }

    public List<Object> possiveisPosicao(String equipa, String posicao, boolean lateral,List<Integer> usados) throws NaoHaJogadorPosicaoException {
        List<Object> list = catalogoEquipas.get(equipa).possiveisPosicao(posicao,lateral,usados);
        if (list.size() == 0) throw new NaoHaJogadorPosicaoException("Não há jogadores para esta posição");
        return list;
    }


    public void addJogador(Jogador jogador, String equipa) throws JogadorExistenteException {
        catalogoEquipas.get(equipa).addJogador(jogador.clone());
    }

    public List<String> listJogadores(String equipa, Collection<Integer> collection){
        return catalogoEquipas.get(equipa).listJogadores (collection);
    }

    public int OverallTitulares(String equipa,List<Integer> titulares, EsquemaTatico esquemaTatico) {
        return catalogoEquipas.get(equipa).overallList (titulares,esquemaTatico);
    }

    public boolean isEmptyEquipa(String equipa) {
        return catalogoEquipas.get(equipa).isEmpty();
    }

    public boolean containsEquipa(String nomeEquipa) {
        return catalogoEquipas.containsKey(nomeEquipa);
    }
}
