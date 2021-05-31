import Atributo.Atributo;

import java.util.List;
import java.util.Map;

public class Avancado extends Jogador {
    private int finalizacao;

    //              Constructors                //

    public Avancado(){
        super();
        this.finalizacao = 0;
    }

    public Avancado(String name, String id, Map<Double, List<Atributo>> atributos,
                    List<String> historico, int finalizacao)
    {
        super(name, id, atributos, historico);
        this.finalizacao = finalizacao;
    }

    public Avancado(Avancado avancado){
        super(avancado);
        this.finalizacao = avancado.getFinalizacao();
    }

    //              Getters and Setters             //

    public int getFinalizacao() {
        return finalizacao;
    }

    public void setFinalizacao(int finalizacao) {
        this.finalizacao = finalizacao;
    }

    public int habilidadeGeral() {
        return 0;
    }

    public Jogador clone() {
        return new Avancado(this);
    }
}
