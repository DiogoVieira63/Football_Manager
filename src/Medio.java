import Atributo.Atributo;

import java.util.List;
import java.util.Map;

public class Medio extends Jogador{
    private int recuperacaoBola;
    private int visao;

    //              Constructors                //

    public Medio(){
        super();
        this.recuperacaoBola = 0;
        this.visao = 0;
    }

    public Medio(String name, String id, Map<Double, List<Atributo>> atributos,
                 List<String> historico, int recuperacaoBola, int visao)
    {
        super(name, id, atributos, historico);
        this.recuperacaoBola = recuperacaoBola;
        this.visao = visao;
    }

    public Medio(Medio medio){
        super(medio);
        this.recuperacaoBola = medio.getRecuperacaoBola();
        this.visao = medio.getVisao();
    }

    //              Getters and Setters             //

    public int getRecuperacaoBola() {
        return recuperacaoBola;
    }

    public void setRecuperacaoBola(int recuperacaoBola) {
        this.recuperacaoBola = recuperacaoBola;
    }

    public int getVisao() {
        return visao;
    }

    public void setVisao(int visao) {
        this.visao = visao;
    }

    public int habilidadeGeral() {
        return 0;
    }

    public Jogador clone() {
        return new Medio(this);
    }
}
