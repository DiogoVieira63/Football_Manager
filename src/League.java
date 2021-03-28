import java.util.ArrayList;
import java.util.List;

public class League {
    private String name;
    private List<Equipa> tabela;

    public League (){
        this.name = "League";
        this.tabela = new ArrayList<>();
    }

    public void addEquipa (Equipa equipa){
        if (getTabela().stream().noneMatch(equipa::equals)){
            equipa.setPositionLeague(getTabela().size());
            getTabela().add(equipa);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Equipa> getTabela() {
        return tabela;
    }

    public void setTabela(List<Equipa> tabela) {
        this.tabela = tabela;
    }
}
