import Atributo.*;
import Exceptions.JogadorExistenteException;
import Exceptions.LinhaIncorretaException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Parser {
    public static void parse() throws LinhaIncorretaException, JogadorExistenteException {
        List<String> linhas = lerFicheiro("output.txt");
        Map<String, Equipa> equipas = new HashMap<>(); //nome, equipa
        Map<String, Jogador> jogadores = new HashMap<>(); //numero, jogador
        List<Jogo> jogos = new ArrayList<>();
        Equipa ultima = null; Jogador j = null;
        String[] linhaPartida;
        Random rand = new Random();
        for (String linha : linhas){
            linhaPartida = linha.split(":", 2);
            switch (linhaPartida[0]){
                case "Equipa":
                    Equipa e = Equipa.parse(linhaPartida[1]);
                    equipas.put(e.getName(), e);
                    ultima = e;
                    break;
                case "Guarda-Redes" :
                    j = GuardaRedes.parse(linhaPartida[1]);
                    jogadores.put(j.getId(), j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.addJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Defesa":
                    j = Defesa.parse(linhaPartida[1]);
                    jogadores.put(j.getId(), j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.addJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Medio":
                    j = Medio.parse(linhaPartida[1]);
                    jogadores.put(j.getId(), j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.addJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Lateral":
                    int i = rand.nextInt(2);
                    if (i == 0){
                        //MEDIO
                        j = Medio.parse(linhaPartida[1]);
                        Lateralidade lateralidade = new Lateralidade(50);
                        j.addAtributo(lateralidade, 0.2);
                        Medio medio = (Medio) j;
                        medio.setLateral(true);
                        jogadores.put(j.getId(), j);
                        if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                        ultima.addJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    }
                    if (i == 1){
                        //AVANCADO
                        j = Avancado.parse(linhaPartida[1]);
                        Lateralidade lateralidade = new Lateralidade(50);
                        j.addAtributo(lateralidade, 0.2);
                        Avancado avancado = (Avancado) j;
                        avancado.setLateral(true);
                        jogadores.put(j.getId(), j);
                        if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                        ultima.addJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    }
                    else{
                        //DEFESA
                        j = Defesa.parse(linhaPartida[1]);
                        Lateralidade lateralidade = new Lateralidade(50);
                        j.addAtributo(lateralidade, 0.2);
                        Defesa defesa = (Defesa) j;
                        defesa.setLateral(true);
                        jogadores.put(j.getId(), j);
                        if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                        ultima.addJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    }
                    break;
                case "Avancado":
                    j = Avancado.parse(linhaPartida[1]);
                    jogadores.put(j.getId(), j);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.addJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Jogo":
                    Jogo jo = Jogo.parse(linhaPartida[1]);
                    jogos.add(jo);
                    break;
                default:
                    throw new LinhaIncorretaException();
            }
        }
        /*
        //debug
        for (Equipa e: equipas.values()){
            System.out.println(e.toString());
        }
        for (Jogo jog: jogos){
            System.out.println(jog.toString());
        }
        */

    }

    public static List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }

}
