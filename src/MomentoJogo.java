import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TimerTask;

public class MomentoJogo {
    private Zonas zonaDoCampo;
    private PosseDeBola posseDeBola;
    private int golosCasa;
    private int golosFora;
    private List<Integer> diferencaCasaFora;


    private enum  PosseDeBola{CASA,FORA}

    private enum Zonas {
        ZONA1 (1),
        ZONA2 (2),
        ZONA3(3);

        public final int valor;

        private Zonas(int valor) {
            this.valor = valor;
        }
    }

    private enum Acontecimentos {PASSE_ZONA1,PASSE_ZONA2,PASSE_ZONA3,OPORTUNIDADE_GOLO}


    public MomentoJogo (List<Integer> diferencaCasaFora){
        zonaDoCampo = Zonas.ZONA2;
        posseDeBola = PosseDeBola.CASA;
        golosCasa = 0;
        golosFora = 0;
        this.diferencaCasaFora = new ArrayList<>(diferencaCasaFora);
    }
    public int getGolosCasa (){
        return golosCasa;
    }

    public int getGolosFora(){
        return golosFora;
    }


    public void setDiferencaCasaFora (List<Integer> list){
        this.diferencaCasaFora = new ArrayList<>(list);
    }

    public Acontecimentos getAcontecimento () {
        Random random = new Random();
        int number = random.nextInt(100);
        switch (zonaDoCampo) {
            case ZONA1:
                if (number < 50) return Acontecimentos.PASSE_ZONA1;
                else if (number < 85) return Acontecimentos.PASSE_ZONA2;
                else if (number < 99) return Acontecimentos.PASSE_ZONA3;
                else return Acontecimentos.OPORTUNIDADE_GOLO;
            case ZONA2:
                if (number < 40) return Acontecimentos.PASSE_ZONA1;
                else if (number < 80) return Acontecimentos.PASSE_ZONA2;
                else if (number < 95) return Acontecimentos.PASSE_ZONA3;
                else return Acontecimentos.OPORTUNIDADE_GOLO;
            case ZONA3:
                if (number < 10) return Acontecimentos.PASSE_ZONA1;
                else if (number < 45) return Acontecimentos.PASSE_ZONA2;
                else if (number < 80) return Acontecimentos.PASSE_ZONA3;
                else return Acontecimentos.OPORTUNIDADE_GOLO;
            default:
                throw new IllegalStateException("Unexpected value: " + zonaDoCampo);
        }
    }
    public int getSucesso (Acontecimentos acontecimento){
        switch (zonaDoCampo) {
            case ZONA1:
                if (acontecimento == Acontecimentos.PASSE_ZONA1) return 15;
                if (acontecimento == Acontecimentos.PASSE_ZONA2) return -15;
                if (acontecimento == Acontecimentos.PASSE_ZONA3) return -40;
                if (acontecimento == Acontecimentos.OPORTUNIDADE_GOLO) return -98;
            case ZONA2:
                if (acontecimento == Acontecimentos.PASSE_ZONA1) return 20;
                if (acontecimento == Acontecimentos.PASSE_ZONA2) return -10;
                if (acontecimento == Acontecimentos.PASSE_ZONA3) return -15;
                if (acontecimento == Acontecimentos.OPORTUNIDADE_GOLO) return -65;
            case ZONA3:
                if (acontecimento == Acontecimentos.PASSE_ZONA1) return 25;
                if (acontecimento == Acontecimentos.PASSE_ZONA2) return -10;
                if (acontecimento == Acontecimentos.PASSE_ZONA3) return -20;
                if (acontecimento == Acontecimentos.OPORTUNIDADE_GOLO) return -10;
            default:
                throw new IllegalStateException("Unexpected value: " + zonaDoCampo);
        }
    }


    public boolean acontcimentoSucesso (int probabilidade, Acontecimentos acontecimento){
        int prob = (int)probabilidade + getSucesso(acontecimento);
        Random random = new Random();
        int number = random.nextInt(100);
        if (number < prob) return true;
        else return false;
    }

    public void segundaParte() {
        this.zonaDoCampo= Zonas.ZONA2;
        this.posseDeBola = PosseDeBola.FORA;
    }


    public void changePosseBola (){
        if (posseDeBola == PosseDeBola.CASA) posseDeBola = PosseDeBola.FORA;
        else posseDeBola = PosseDeBola.CASA;
    }

    public boolean change (Acontecimentos acontecimento, boolean sucesso){
        if (!sucesso) changePosseBola();
        switch (acontecimento){
            case PASSE_ZONA1: ;
                if (sucesso) zonaDoCampo = Zonas.ZONA1;
                else zonaDoCampo=Zonas.ZONA3;
                break;
            case PASSE_ZONA2:
                zonaDoCampo = Zonas.ZONA2;
                break;
            case PASSE_ZONA3:
                if (sucesso) zonaDoCampo = Zonas.ZONA3;
                else zonaDoCampo = Zonas.ZONA1;
                break;
            case OPORTUNIDADE_GOLO:
                if (sucesso) {
                    changePosseBola();
                    zonaDoCampo= Zonas.ZONA2;
                    return true;
                }
                else zonaDoCampo = Zonas.ZONA1;
        }
        return false;
    }

    public int getProbabilidade (List<Integer> list){
        if (posseDeBola == PosseDeBola.CASA) return list.get(zonaDoCampo.valor - 1);
        else return - (list.get(3 - zonaDoCampo.valor));

    }


    public void run (){
        Acontecimentos acontecimento = getAcontecimento();
        int probabilidade = 50 + getProbabilidade(diferencaCasaFora);
        boolean sucesso = acontcimentoSucesso(probabilidade,acontecimento);
        boolean golo = change (acontecimento,sucesso);
        if (golo){
            if (posseDeBola == PosseDeBola.FORA) {
                this.golosCasa++;
            }
            else {
                this.golosFora++;
            }
        }
    }

}
