import Atributo.*;
import Exceptions.EquipaJaExisteException;
import Exceptions.JogadorExistenteException;
import Exceptions.LinhaIncorretaException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Parser {
    public static Model parse(String filename) throws LinhaIncorretaException, JogadorExistenteException {
        Model model = new Model();
        List<String> linhas = lerFicheiro(filename);
        Equipa ultima = null; Jogador j = null;
        String[] linhaPartida;
        Random rand = new Random();
        for (String linha : linhas){
            //System.out.println(linha);
            linhaPartida = linha.split(":", 2);
            switch (linhaPartida[0]){
                case "Equipa":
                    Equipa e = Equipa.parse(linhaPartida[1]);
                    try {
                        model.addEquipa(e);
                        ultima = e;
                    }
                    catch (EquipaJaExisteException exception){
                        System.out.println(exception.getMessage());
                    }
                    break;
                case "Guarda-Redes" :
                    j = GuardaRedes.parse(linhaPartida[1]);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.addJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Defesa":
                    j = Defesa.parse(linhaPartida[1], false);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.addJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Medio":
                    j = Medio.parse(linhaPartida[1],false);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.addJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Lateral":
                    int i = rand.nextInt(3);
                    if (i == 0){
                        //MEDIO
                        j = Medio.parse(linhaPartida[1], true);
                        if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                        ultima.addJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    }
                    else if (i == 1){
                        //AVANCADO
                        j = Avancado.parse(linhaPartida[1], true);
                        if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                        ultima.addJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    }
                    else{
                        //DEFESA
                        j = Defesa.parse(linhaPartida[1], true);
                        if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                        ultima.addJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    }
                    break;
                case "Avancado":
                    j = Avancado.parse(linhaPartida[1], false);
                    if (ultima == null) throw new LinhaIncorretaException(); //we need to insert the player into the team
                    ultima.addJogador(j.clone()); //if no team was parsed previously, file is not well-formed
                    break;
                case "Jogo":
                    Jogo jo = Jogo.parse(linhaPartida[1],model.getCatalogoEquipas());
                    model.addJogo(jo);
                    break;
                default:
                    throw new LinhaIncorretaException();
            }
        }
        return model;
    }

    public static List<String> lerFicheiro(String nomeFich) {
        List<String> lines;
        try { lines = Files.readAllLines(Paths.get(nomeFich), StandardCharsets.UTF_8); }
        catch(IOException exc) { lines = new ArrayList<>(); }
        return lines;
    }

}
