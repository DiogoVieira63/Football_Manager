import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class CatalogoEquipas {
    private Map<String, Equipa> catalogoEquipas;

    //              Constructors                //

    public CatalogoEquipas(){
        this.catalogoEquipas = new HashMap<>();
    }

    public CatalogoEquipas (Map<String, Equipa> catalogoEquipas){
        this.catalogoEquipas = catalogoEquipas
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
    }

    public CatalogoEquipas (CatalogoEquipas catalogo){
        this.catalogoEquipas = catalogo.getCatalogoEquipas();
    }

    //              Getters and Setters             //

    public Map<String, Equipa> getCatalogoEquipas() {
        return this.catalogoEquipas
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
    }

    public void setCatalogoEquipas(Map<String, Equipa> catalogoEquipas) {
        this.catalogoEquipas = catalogoEquipas
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().clone()));
    }
}
