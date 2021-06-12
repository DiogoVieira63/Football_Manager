import jdk.dynalink.linker.LinkerServices;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class EsquemaTatico implements Serializable {
    private static final long serialVersionUID = 6773379586863421241L;
    private int defesas;
    private int medios;
    private int atacantes;

    //              Constructors                //

    public EsquemaTatico(){
        this.defesas = 4;
        this.medios = 3;
        this.atacantes = 3;
    }

    public EsquemaTatico(int defesas, int medios, int atacantes){
        this.defesas = defesas;
        this.medios = medios;
        this.atacantes = atacantes;
    }

    public EsquemaTatico(EsquemaTatico esquema){
        this.defesas = esquema.getDefesas();
        this.medios = esquema.getMedios();
        this.atacantes = esquema.getAtacantes();
    }

    //              Getters and Setters             //

    public int getDefesas() {
        return defesas;
    }

    public void setDefesas(int defesas) {
        this.defesas = defesas;
    }

    public int getMedios() {
        return medios;
    }

    public void setMedios(int medios) {
        this.medios = medios;
    }

    public int getAtacantes() {
        return atacantes;
    }

    public void setAtacantes(int atacantes) {
        this.atacantes = atacantes;
    }

    public EsquemaTatico clone(){
        return new EsquemaTatico(this);
    }

    public List<String> posicoes (){
        List<String> list = new ArrayList<>();
        list.add("Posição 1:Guarda-redes(GR)");
        list.add("Posição 2:Defesa Lateral(DL)");
        list.add("Posição 3:Defesa Central(DC)");
        list.add("Posição 4:Defesa Central(DC)");
        list.add("Posição 5:Defesa Lateral(DL)");
        list.add("Posição 6:Medio Centro(MC)");
        list.add("Posição 7:Medio Centro(MC)");
        if (medios == 4)
            list.add("Posição 8:Medio Lateral(ML)");
        else
            list.add("Posição 8:Medio Centro(MC)");
        if (medios == 4)
            list.add("Posição 9:Medio Lateral(ML)");
        else
            list.add("Posição 9:Avancado Centro(AC)");
        if (atacantes == 2)
            list.add("Posição 10:Avancado Centro(AC)");
        else
            list.add("Posição 10:Avancado Lateral(AL)");
        if (atacantes == 2)
            list.add("Posição 11:Avancado Centro(AC)");
        else
            list.add("Posição 11:Avancado Lateral(AL)");
        return list;
    }

    public Map.Entry<String,Boolean> infoPosicao (int number){
        switch (number){
            case 1:
                return Map.entry("GuardaRedes",false);
            case 2:
            case 5:
                return Map.entry("Defesa",true);
            case 3:
            case 4:
                return Map.entry("Defesa",false);
            case 6:
            case 7:
                return Map.entry("Medio",false);
            case 8:
                if (medios == 4)return Map.entry("Medio",true);
                else return Map.entry("Medio",false);
            case 9:
                if (medios == 4)return Map.entry("Medio",true);
                else return Map.entry("Avancado",false);
            case 10:
            case 11:
                if (atacantes == 2) return Map.entry("Avancado",false);
                else return Map.entry("Avancado",true);
        }
        return null;
    }

    @Override
    public String toString() {
        return "EsquemaTatico{" +
                "defesas=" + defesas +
                ", medios=" + medios +
                ", atacantes=" + atacantes +
                '}';
    }
}
