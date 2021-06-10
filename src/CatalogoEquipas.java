import Exceptions.JogadorExistenteException;

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

    public void adicionarEquipa (Equipa equipa){
        catalogoEquipas.put(equipa.getName(),equipa);
    }

    public List<String> nomesOrdenados (){
        Set <String> set = new TreeSet<>(catalogoEquipas.keySet());
        return new ArrayList<String>(set);
    }


    public List<Object> nomesOrdenadosEquipa (String equipa){
        return catalogoEquipas.get(equipa).organizadoNumero();
    }

    public List<Object> infoJogador (String equipa, int jogador){
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

    public Map<Integer, Integer> overallJogadores(String nomeCasa) {
        return catalogoEquipas.get(nomeCasa).mapOverall();
    }

    public Set<Integer> getNumeros(String equipa) {
        return catalogoEquipas.get(equipa).getNumeros();
    }

    public List<Object> possiveisPosicao(String equipa, String posicao, boolean lateral) {
        return catalogoEquipas.get(equipa).possiveisPosicao(posicao,lateral);
    }
}
