import Atributo.Atributo;

import java.util.List;
import java.util.Map;

public class Defesa extends Jogador{
    private int marcacao;
    private boolean lateral;

    //              Constructors                //

    public Defesa(){
        super();
        this.marcacao = 0;
        this.lateral = false;
    }

    public Defesa(String name, String id, Map<Double, List<Atributo>> atributos,
                 List<String> historico, int marcacao, boolean lateral)
    {
        super(name, id, atributos, historico);
        this.marcacao = marcacao;
        this.lateral = lateral;
    }

    public Defesa(Defesa defesa){
        super(defesa);
        this.marcacao = defesa.getMarcacao();
        this.lateral = defesa.getLateral();
    }

    //              Getters and Setters             //

    public int getMarcacao() {
        return marcacao;
    }

    public void setMarcacao(int marcacao) {
        this.marcacao = marcacao;
    }

    public boolean getLateral() {
        return lateral;
    }

    public void setLateral(boolean lateral) {
        this.lateral = lateral;
    }

    public int habilidadeGeralEspecifica() {
        return (int) (super.habilidadeGeral() + (this.marcacao * 0.3));
    }

    public Jogador clone() {
        return new Defesa(this);
    }
}
