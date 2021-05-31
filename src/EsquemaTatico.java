public class EsquemaTatico {
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
}
