import java.io.Serializable;
import java.sql.ClientInfoStatus;
import java.util.*;
import java.util.stream.Collectors;
import Exceptions.*;

public class Equipa implements Serializable {
    private static final long serialVersionUID = -5626352478359403106L;
    private String name;
    private Map<Integer, Jogador> listaJogadores;

    //              Constructors                //

    public Equipa() {
        this.name = "";
        this.listaJogadores = new HashMap<Integer, Jogador>();
    }

    public Equipa(String name){
        this.name = name;
        this.listaJogadores = new HashMap<Integer, Jogador>();
    }

    public Equipa (String name, Map<Integer, Jogador> listaJogadores, boolean lateral){
        this.name = name;
        this.listaJogadores = listaJogadores
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, j -> j.getValue().clone()));
    }

    public Equipa (Equipa equipa){
        this.name = equipa.getName();
        this.listaJogadores = equipa.getListaJogadores();
    }

    public void addJogador (Jogador jogador) throws JogadorExistenteException {
        if (this.listaJogadores.containsKey(jogador.getId()))
            throw new JogadorExistenteException("O Jogador " + jogador.getName() + "já pertence à equipa " + this.name);
        else {
            this.listaJogadores.put(jogador.getId(), jogador);
            jogador.addEquipaHistorico(this.name);
        }
    }

    public void transferenciaJogador (int Idjogador, Equipa novaEquipa) throws JogadorExistenteException {
        Jogador jogador = listaJogadores.get(Idjogador);
        int size = jogador.getHistorico().size();
        if (jogador.getHistorico().get(size-1).equals(novaEquipa.name))
            throw new JogadorExistenteException("O Jogador " + jogador.getName() +"já pertence à equipa " + novaEquipa.getName());
        else{
            this.listaJogadores.remove(jogador.getId());
            int number = jogador.getId();
            while (novaEquipa.containsId(number)){
                number = Jogador.randomBetween(0,100);
            }
            jogador.setId(number);
            novaEquipa.addJogador(jogador);
        }
    }

    public double mediaGeralEquipa (Equipa equipa){
        double total=0;
        for (Jogador player : this.listaJogadores.values()){
            total += player.habilidadeGeralEspecifica();
        }
        return total/this.listaJogadores.size();
    }

    public Jogador getJogador(Integer idJog){
        return this.listaJogadores.get(idJog).clone();
    }

    public int habilidadeEquipa (){
        int total = 0;
        for (Jogador jogador : this.listaJogadores.values()){
            total += jogador.habilidadeGeralEspecifica();
        }
        return total/getListaJogadores().size();
    }

    public static Equipa parse(String input){
        String[] campos = input.split(",");
        return new Equipa(campos[0]);
    }

    //              Getters and Setters             //

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map<Integer, Jogador> getListaJogadores() {
        return listaJogadores
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, j -> j.getValue().clone()));
    }


    public void setListaJogadores(Map<Integer, Jogador> listaJogadores) {
        this.listaJogadores = listaJogadores
                .entrySet()
                .stream()
                .collect(Collectors.toMap(Map.Entry::getKey, j -> j.getValue().clone()));
    }


    public List<Object> organizadoNumero () throws EquipaSemJogadoresException{
        if (listaJogadores.size() == 0) throw new EquipaSemJogadoresException("Equipa não tem jogadores");
        Set<Integer> set = new TreeSet<>(listaJogadores.keySet());
        List <Object> list = new ArrayList<>();
        for (Integer number : set){
            Jogador jogador = listaJogadores.get(number);
            list.add(Map.entry(number," - " + jogador + " - " + jogador.habilidadeGeralEspecifica()));
        }
        list.add(Map.entry(-1,""));
        list.add(Map.entry(-1, "Overall Equipa:" + this.habilidadeEquipa()));
        return list;
    }

    public List<String> infoJogador (int number){
        return listaJogadores.get(number).infoJogador();
    }

    public Equipa clone(){
        return new Equipa(this);
    }

    public Set<Integer> getNumeros (){
        return listaJogadores.keySet();
    }


    public boolean containsId (int id){
        return listaJogadores.containsKey(id);
    }


    public Map<Integer,Integer> mapOverall (EquipaJogo equipaJogo){
        Map<Integer,Integer> map = new HashMap<>();
        List <Integer> titulares = equipaJogo.getTitulares();
        for (int i = 0;i < 11;i++){
            Map.Entry<String,Boolean> info = equipaJogo.infoPosicao(i + 1);
            int id = titulares.get(i);
            Jogador jogador = listaJogadores.get(id);
            double percentagem = (jogador.getClass().getName().equals(info.getKey()) && jogador.isLateral() == info.getValue()) ? 1.0 : 0.75;
            map.put(jogador.getId(), (int) ((int)jogador.habilidadeGeralEspecifica() * percentagem));
        }
        for (Substituicao sub : equipaJogo.getSubstituições()){
            int index = titulares.indexOf(sub.getOut());
            Map.Entry<String,Boolean> info = equipaJogo.infoPosicao(index + 1);
            Jogador jogador = listaJogadores.get(sub.getIn());
            double percentagem = (jogador.getClass().getName().equals(info.getKey()) && jogador.isLateral() == info.getValue()) ? 1.0 : 0.75;
            map.put(jogador.getId(), (int) ((int)jogador.habilidadeGeralEspecifica() * percentagem));
        }
        return map;
    }


    public List<Object> possiveisPosicao (String posicao,boolean lateral, List<Integer> usados){
        Set<Integer> set = new TreeSet<>(listaJogadores.keySet());
        List<Object> list = new ArrayList<>();
        for (Integer number: set){
            Jogador jogador = listaJogadores.get(number);
            String className = jogador.getClass().getName();
            if (className.equals(posicao) || (!posicao.equals("GuardaRedes") && lateral == jogador.isLateral())){
                if (!usados.contains(jogador.getId())) {
                    int habilidade = jogador.habilidadeGeralEspecifica();
                    habilidade *= jogador.percentagemPosicao(posicao,lateral);
                    if (posicao.equals("GuardaRedes") || !(className.equals("GuardaRedes")))
                        list.add(Map.entry(jogador.getId(), " - " + jogador +  " - " + habilidade));
                }
            }
        }
        return list;
    }

    public int overallList(List<Integer> titulares, EsquemaTatico esquemaTatico) {
        int total = 0;
        int posicao = 1;
        for (Integer number : titulares){
            Jogador jogador = listaJogadores.get(number);
            Map.Entry<String,Boolean> info = esquemaTatico.infoPosicao(posicao);
            double percentagem = (jogador.getClass().getName().equals(info.getKey()) && jogador.isLateral() == info.getValue()) ? 1.0 : 0.75;
            total += jogador.habilidadeGeralEspecifica() * percentagem;
            posicao++;
        }
        return (int) total/titulares.size();
    }


    public List<String> listJogadores(Collection<Integer> collection){
        List<String> list = new ArrayList<>();
        for (Integer number : collection){
            Jogador jogador = listaJogadores.get(number);
            list.add(jogador.getId() + " - "  + jogador.toString());
        }
        return list;
    }

    public boolean isEmpty() {
        return listaJogadores.isEmpty();
    }


}
