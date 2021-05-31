import Atributo.*;

import java.util.List;
import java.util.Map;

public class Avancado extends Jogador {
    private int finalizacao;
    private boolean lateral;

    //              Constructors                //

    public Avancado(){
        super();
        this.finalizacao = 0;
        this.lateral = false;
    }

    public Avancado(String name, String id, Map<Double, List<Atributo>> atributos,
                    List<String> historico, int finalizacao, boolean lateral)
    {
        super(name, id, atributos, historico);
        this.finalizacao = finalizacao;
        this.lateral = lateral;
    }

    public Avancado(Avancado avancado){
        super(avancado);
        this.finalizacao = avancado.getFinalizacao();
        this.lateral = avancado.getLateral();
    }

    //              Getters and Setters             //

    public int getFinalizacao() {
        return finalizacao;
    }

    public void setFinalizacao(int finalizacao) {
        this.finalizacao = finalizacao;
    }

    public boolean getLateral() {
        return lateral;
    }

    public void setLateral(boolean lateral) {
        this.lateral = lateral;
    }

    public int habilidadeGeralEspecifica() {
        return (int) (super.habilidadeGeral() + (this.finalizacao * 0.3));
    }

    public Jogador clone() {
        return new Avancado(this);
    }
}
