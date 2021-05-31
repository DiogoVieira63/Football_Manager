import Atributo.Atributo;

import java.util.AbstractMap;
import java.util.List;
import java.util.Map;

public class GuardaRedes extends Jogador {
    private int elasticidade;
    private int reflexos;

    //              Constructors                //

    public GuardaRedes (){
        super();
        this.elasticidade = 0;
        this.reflexos = 0;
    }

    public GuardaRedes(String name, String id, Map<Double, List<Atributo>> atributos,
                       List<String> historico, int elasticidade, int reflexos){
        super(name, id, atributos, historico);
        this.elasticidade = elasticidade;
        this.reflexos = reflexos;
    }

    public GuardaRedes (GuardaRedes guardaRedes){
        super(guardaRedes);
        this.elasticidade = guardaRedes.getElasticidade();
        this.reflexos = guardaRedes.getReflexos();
    }

    //              Getters and Setters             //

    public int getElasticidade() {
        return elasticidade;
    }

    public void setElasticidade(int elasticidade) {
        this.elasticidade = elasticidade;
    }

    public int getReflexos() {
        return reflexos;
    }

    public void setReflexos(int reflexos) {
        this.reflexos = reflexos;
    }

    public int habilidadeGeral (){
        return 10;
    }

    public Jogador clone(){
        return new GuardaRedes(this);
    }

}
