import Atributo.Atacante;
import Atributo.Defensivo;
import Atributo.Fisico;
import Atributo.Tecnico;

public class Atributos {
    Atacante ataque;
    Defensivo defensivo;
    Tecnico tecnico;
    Fisico fisico;


    public Atributos (){
        this.ataque = new Atacante(70,70);
        this.defensivo = new Defensivo(70,70);
        this.tecnico = new Tecnico(70,70);
        this.fisico = new Fisico(70,70);
    }

    public Atributos(int velocidade, int resistencia, int destreza, int impulsao, int jogoDecCabeca, int remate, int capacidadePasse,int capacidadeDefensiva) {
        this.ataque = new Atacante(remate,jogoDecCabeca);
        this.defensivo = new Defensivo(impulsao,capacidadeDefensiva);
        this.tecnico = new Tecnico(capacidadePasse,destreza);
        this.fisico = new Fisico(velocidade,resistencia);
    }

    public Atacante getAtaque() {
        return ataque;
    }

    public void setAtaque(Atacante ataque) {
        this.ataque = ataque;
    }

    public Defensivo getDefensivo() {
        return defensivo;
    }

    public void setDefensivo(Defensivo defensivo) {
        this.defensivo = defensivo;
    }

    public Tecnico getTecnico() {
        return tecnico;
    }

    public void setTecnico(Tecnico tecnico) {
        this.tecnico = tecnico;
    }

    public Fisico getFisico() {
        return fisico;
    }

    public void setFisico(Fisico fisico) {
        this.fisico = fisico;
    }
}
