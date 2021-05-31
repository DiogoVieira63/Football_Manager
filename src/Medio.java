import Atributo.Atributo;

import java.util.List;
import java.util.Map;

public class Medio extends Jogador{
    private int recuperacaoBola;
    private int visao;
    private boolean lateral;

    //              Constructors                //

    public Medio(){
        super();
        this.recuperacaoBola = 0;
        this.visao = 0;
        this.lateral = false;
    }

    public Medio(String name, String id, Map<Double, List<Atributo>> atributos,
                 List<String> historico, int recuperacaoBola, int visao, boolean lateral)
    {
        super(name, id, atributos, historico);
        this.recuperacaoBola = recuperacaoBola;
        this.visao = visao;
        this.lateral = lateral;
    }

    public Medio(Medio medio){
        super(medio);
        this.recuperacaoBola = medio.getRecuperacaoBola();
        this.visao = medio.getVisao();
        this.lateral = medio.getLateral();
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

    public boolean getLateral() {
        return lateral;
    }

    public void setLateral(boolean lateral) {
        this.lateral = lateral;
    }

    public int habilidadeGeralEspecifica() {
        return (int) (super.habilidadeGeral() + (this.recuperacaoBola * 0.15 + this.visao * 0.15));
    }

    public Jogador clone() {
        return new Medio(this);
    }
}
