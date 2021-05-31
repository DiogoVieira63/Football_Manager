import Atributo.Atributo;

import java.util.List;
import java.util.Map;

public class Defesa extends Jogador{
    private int marcacao;

    //              Constructors                //

    public Defesa(){
        super();
        this.marcacao = 0;
    }

    public Defesa(String name, String id, Map<Double, List<Atributo>> atributos,
                 List<String> historico, int marcacao)
    {
        super(name, id, atributos, historico);
        this.marcacao = marcacao;
    }

    public Defesa(Defesa defesa){
        super(defesa);
        this.marcacao = getMarcacao();
    }

    //              Getters and Setters             //

    public int getMarcacao() {
        return marcacao;
    }

    public void setMarcacao(int marcacao) {
        this.marcacao = marcacao;
    }

    public int habilidadeGeral() {
        return 0;
    }

    public Jogador clone() {
        return new Defesa(this);
    }
}
