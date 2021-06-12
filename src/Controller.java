import Exceptions.EquipaJaExisteException;
import Exceptions.JogadorExistenteException;
import Exceptions.LinhaIncorretaException;
import Exceptions.NaoHaJogadorPosicaoException;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ClientInfoStatus;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Controller {
    private Model model;
    private int menu;
    private int submenu;
    private int opcao;

    public Controller (Model model){
        this.model = model;
        this.menu = 0;
        this.submenu = 0;
        this.opcao = 0;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    private static void pressAnyKeyToContinue()
    {
        View.printFrase("Enter para continuar");
        try
        {
            System.in.read();
        }
        catch(Exception e)
        {}
    }

    @SuppressWarnings("unchecked")
    private boolean containsId (Integer id, List <Object> list){
        if (id == -1) return false;
        for (int i = 0;i < list.size();i++){
            Object obj = list.get(i);
            Map.Entry<Integer,String> entry = (Map.Entry<Integer,String>) obj;
            if (entry.getKey().equals(id)) return true;
        }
        return false;
    }



    public int selecionarEquipa (List<String> titulosEquipas){
        int opcao;
        Scanner scanner = new Scanner(System.in);
        String[] line = scanner.nextLine().split("//s+");
        if (line.length == 1) {
            opcao = getInt(line[0]);
            if (opcao >= 1 && opcao <= titulosEquipas.size()) {
                if (!model.isEmptyEquipa(titulosEquipas.get(opcao-1))) return opcao;
                else {
                    View.printFrase("A equipa não contém jogadores");
                    pressAnyKeyToContinue();
                }
            } else if (opcao == 0) {
                menu = 0;
            }
            else {
                View.printFrase("Erro: Não há opção \"" + line[0] + "\"");
                pressAnyKeyToContinue();
            }
        } else {
            View.printFrase("ERRO -Coloque apenas o número");
            pressAnyKeyToContinue();
        }
        return 0;
    }

    public int getInt (String line){
        int opcao;
        try {
            opcao = Integer.parseInt(line);
        } catch (NumberFormatException e) {
            opcao = -1;
        }
        return opcao;
    }

    public int askInt (){
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()){
            return scanner.nextInt();
        }
        else return -1;
    }

    @SuppressWarnings("unchecked")
    public boolean isPossible (List<Object> list,int number){
        for (Object obj : list){
            Map.Entry<Integer,String> entry = (Map.Entry<Integer,String>) obj;
            if (number == entry.getKey()) return true;
        }
        return false;
    }

    public boolean carregarLogs () {
        Scanner scanner = new Scanner(System.in);
        File temp = new File("logs.txt");
        if (temp.exists()) {
            do {
                View.clearScreen();
                View.printFrase("Quer carregar o ficheiro \"logs.txt\"");
                View.printOpcao("0. Não");
                View.printOpcao("1. Sim");
                View.printPrompt("Choose Option");
                String[] line;
                line = scanner.nextLine().split("//s+");
                if (line.length == 1) {
                    int number = getInt(line[0]);
                    if (number == 0) return false;
                    else if (number == 1) return true;
                    else {
                        View.printFrase("Erro: Opcão \"" + line[0] + "\" não existe");
                        pressAnyKeyToContinue();
                    }
                } else {
                    View.printFrase("Erro: Coloque apenas o número");
                    pressAnyKeyToContinue();
                }
            }while (true);
        }
        return false;
    }

    public boolean selecionarTitulares (EquipaJogo equipaJogo){
        Scanner scanner = new Scanner(System.in);
        String[] line;
        int number = -1;
        do{
            View.clearScreen();
            View.printTitulo("Esquema Tático");
            View.printOpcao("0. 4-3-3");
            View.printOpcao("1. 4-4-2");
            View.printPrompt("Choose Option");
            line =  scanner.nextLine().split("//s+");
            if (line.length == 1) {
                number = getInt(line[0]);
            }
            else {
                View.printFrase("Erro: Coloque apenas o número");
                pressAnyKeyToContinue();
            }
        }while (number != 0 && number != 1);
        if (number == 1)equipaJogo.setEsquemaTatico(new EsquemaTatico(4,4,2));
        else equipaJogo.setEsquemaTatico(new EsquemaTatico(4,3,3));
        List<String> posicoes = equipaJogo.posicoes();
        for (int i = 1; i <= 11;i++){
                boolean inserted = false;
            do {
                View.clearScreen();
                View.printFrase(posicoes.get(i-1));
                View.printFrase("Nr - Nome - Overall");
                Map.Entry<String,Boolean> entry = equipaJogo.infoPosicao(i);
                List<Object> possiveis = null;
                try {
                    possiveis = model.possiveisPosicao(equipaJogo.getIdEquipa(), entry.getKey(),entry.getValue(),equipaJogo.getTitulares());
                }
                catch (NaoHaJogadorPosicaoException e){
                    View.clearScreen();
                    View.printFrase(posicoes.get(i-1));
                    View.printFrase(e.getMessage());
                    menu = 0;
                    pressAnyKeyToContinue();
                    return false;
                }
                View.printPairOrganizedCollection(possiveis);
                View.printPrompt("Posição" + i);
                line =  scanner.nextLine().split("//s+");
                if (line.length == 1) {
                    int jogador = getInt(line[0]);
                    if (isPossible(possiveis,jogador)) {
                        equipaJogo.addTitular(jogador);
                        inserted = true;
                    }
                    else {
                        View.printFrase("Erro: Input inválio");
                        pressAnyKeyToContinue();
                    }
                }
                else {
                    View.printFrase("Erro: Coloque apenas o número");
                    pressAnyKeyToContinue();
                }
            }while (!inserted);
        }
        do {
            View.clearScreen();
            View.printTitulo("Nr de Substituições (0-3)");
            View.printPrompt("Resposta");
            line =  scanner.nextLine().split("//s+");
            if (line.length == 1){
                number = getInt(line[0]);
            }
            else {
                View.printFrase("Erro: Coloque apenas o número");
                pressAnyKeyToContinue();
            }
        }while (!(number>=0 && number<= 3));
        for (int i = 0;i < number;i++){
            int sair;
            do {
                View.clearScreen();
                View.printTitulo("Substituicao nº" + (i+1));
                View.printSimpleOrganizedCollection(posicoes,false);
                View.printFrase("");
                View.printSimpleOrganizedCollection(model.ListJogadores(equipaJogo.getIdEquipa(),equipaJogo.getTitulares()),false);
                View.printPrompt("Jogador a sair");
                line =  scanner.nextLine().split("//s+");
                if (line.length == 1){
                    sair = getInt(line[0]);
                }
                else {
                    View.printFrase("Erro: Coloque apenas o número");
                    sair = -1;
                    pressAnyKeyToContinue();
                }
            } while (!equipaJogo.isTitular(sair) || equipaJogo.inSubstituicao(sair));
            int posicao = equipaJogo.getTitulares().indexOf(sair) + 1;
            Map.Entry<String,Boolean> entry = equipaJogo.infoPosicao(posicao);
            int entrar;
            List<Object> possiveis= null;
            do {
                View.clearScreen();
                View.printTitulo("Substituicao nª" + (i+1));
                View.printFrase(posicoes.get(posicao -1));
                View.printFrase("");
                try {
                    possiveis = model.possiveisPosicao(equipaJogo.getIdEquipa(), entry.getKey(),entry.getValue(),equipaJogo.getTitulares());
                    View.printPairOrganizedCollection(possiveis);
                    View.printPrompt("Jogador a entrar");
                    line =  scanner.nextLine().split("//s+");
                    if (line.length == 1){
                        entrar = getInt(line[0]);
                    }
                    else {
                        View.printFrase("Erro: Coloque apenas o número");
                        entrar = -1;
                        pressAnyKeyToContinue();
                    }
                }
                catch (NaoHaJogadorPosicaoException e){
                    View.clearScreen();
                    entrar = -1;
                    View.printFrase("Não há jogadores disponíveis para esta posição");
                    pressAnyKeyToContinue();
                    i-= 1;
                    break;
                }
            } while (!isPossible(possiveis,entrar) || equipaJogo.inSubstituicao(entrar));
            if (entrar != -1) equipaJogo.addSubstituicao(sair,entrar);
        }
        return true;
    }





    @SuppressWarnings("unchecked")
    public boolean run ()  {
        boolean out = false;
        Scanner scanner = new Scanner(System.in);
        String equipa = "";
        int idJogador = 0;
        String line;
        String[] pieces;
        List<String> titulosEquipas = model.nomesEquipasOrdenados();
        List<EquipaJogo> equipaJogoList = new ArrayList<>();
        View.clearScreen();
        switch ((int)menu){
            case 0:
                submenu = 0;
                View.menuPrincipal();
                line = scanner.nextLine();
                pieces = line.split("\\s+");
                if (pieces.length == 1) {
                    opcao = 0;
                    try {
                        opcao = Integer.parseInt(pieces[0]);
                    } catch (NumberFormatException e) {
                        View.printFrase("ERRO: Coloque apenas o número");
                        pressAnyKeyToContinue();
                    }
                    if (opcao >= 1 && opcao <= 5) {
                        menu = opcao;
                    }
                    else if (opcao == 6) out = true;
                }
                else {
                    View.printFrase("ERRO -Coloque apenas o número");
                    pressAnyKeyToContinue();
                }
                break;
            case 1:
                if (equipaJogoList.size() == 2){
                    View.printTitulo("Jogo vai começar");
                    EquipaJogo casa = equipaJogoList.get(0);
                    EquipaJogo fora = equipaJogoList.get(1);
                    View.printFrase("Casa - " + model.OverallTitulares(casa) + " vs " + model.OverallTitulares(fora) + " - Fora");
                    pressAnyKeyToContinue();
                    String resultado = model.efetuarJogo(casa,fora);
                    View.clearScreen();
                    View.printFrase(resultado);
                    pressAnyKeyToContinue();
                    menu = 0;
                    equipaJogoList.clear();
                    break;
                }
                List<String> equipasTemp = new ArrayList<>(titulosEquipas);
                if (equipaJogoList.size() == 1) equipasTemp.remove(equipaJogoList.get(0).getIdEquipa());
                    View.clearScreen();
                    View.printTitulo("Selecionar Equipa");
                    View.printSimpleOrganizedCollection(equipasTemp,true);
                    View.printOpcao("0. Voltar atrás");
                    View.printPrompt("Choose Option");
                    if ((opcao = selecionarEquipa(equipasTemp)) != 0){
                        equipa = equipasTemp.get(opcao-1);
                        EquipaJogo equipaJogo = new EquipaJogo(equipa,model.getNumeros(equipa));
                        if (selecionarTitulares(equipaJogo)) equipaJogoList.add(equipaJogo);
                        else equipaJogoList.clear();
                    }
                    break;
            case 2:
                switch (submenu) {
                    case 0:
                        View.printTitulo("Selecionar Equipa");
                        View.printSimpleOrganizedCollection(titulosEquipas,true);
                        View.printOpcao("0. Voltar atrás");
                        View.printPrompt("Choose Option");
                        if ((opcao = selecionarEquipa(titulosEquipas)) != 0){
                            equipa = titulosEquipas.get(opcao-1);
                            submenu = 1;
                        }
                        break;
                    case 1:
                        View.clearScreen();
                        List<Object> list = model.nomesJogadoresOrdenado(equipa);
                        View.printTitulo("Selecionar Jogador");
                        View.printPairOrganizedCollection(list);
                        View.printOpcao("z. Voltar atrás");
                        View.printOpcao("r. Ver resultados Equipa");
                        View.printPrompt("Choose Option");
                        pieces = scanner.nextLine().split("\\s+");
                        if (pieces.length == 1) {
                            if (pieces[0].equals("z")) submenu = 0;
                            else if (pieces[0].equals("r")) submenu = 4;
                            else{
                                int number = getInt(pieces[0]);
                                if (containsId(number, list)) {
                                    idJogador = number;
                                    submenu = 2;
                                }
                                else {
                                    View.printFrase("Erro: Não há opção \"" + pieces[0] + "\"");
                                    pressAnyKeyToContinue();
                                }
                            }
                        }
                        else {
                            View.printFrase("Erro: Coloque apenas um número ou uma letra");
                            pressAnyKeyToContinue();
                        }
                        break;
                    case 2:
                        View.clearScreen();
                        View.printTitulo("Info Jogador");
                        View.printSimpleOrganizedCollection(model.infoJogador(equipa, idJogador),false);
                        View.printOpcao("0. Voltar Atrás");
                        View.printOpcao("1. Transferir Jogador");
                        View.printPrompt("Choose Option");
                        pieces = scanner.nextLine().split("\\s+");
                        if (pieces.length == 1){
                            int number = getInt(pieces[0]);
                            if (number == 0) submenu = 1;
                            else if (number == 1) submenu = 3;
                            else {
                                View.printFrase("Erro: Não há opcão: " + pieces[0]);
                            }
                        }
                        else {
                            View.printFrase("ERRO -Coloque apenas o número");
                            pressAnyKeyToContinue();
                        }
                        break;
                    case 3:
                        View.clearScreen();
                        View.printTitulo("Equipa a transferir");
                        List <String> aTransferir = new ArrayList<>(titulosEquipas);
                        aTransferir.remove(equipa);
                        View.printSimpleOrganizedCollection(aTransferir,true);
                        View.printOpcao("0. Voltar atrás");
                        View.printPrompt("Choose Option");
                        pieces = scanner.nextLine().split("\\s+");
                        if (pieces.length == 1){
                            opcao = getInt(pieces[0]);
                            if (opcao >= 1 && opcao <= aTransferir.size()) {
                                View.clearScreen();
                                String novaEquipa = aTransferir.get(opcao-1);
                                model.transferirJogador(equipa,idJogador,novaEquipa);
                                View.printFrase("Jogador transferido para "+ novaEquipa);
                                pressAnyKeyToContinue();
                                equipa = novaEquipa;
                                submenu = 1;
                            } else if (opcao == 0) {
                                submenu= 2;
                            } else {
                                View.printFrase("ERRO - Opção inválida");
                                pressAnyKeyToContinue();
                            }
                        }
                        else {
                            scanner.nextLine();
                            View.printFrase("ERRO -Coloque apenas o número");
                            pressAnyKeyToContinue();
                        }
                        break;
                    case 4:
                        View.printTitulo("Resultados");
                        View.printSimpleOrganizedCollection(model.getJogosEquipa(equipa),false);
                        pressAnyKeyToContinue();
                        submenu = 1;
                        break;
                }
                break;
            case 3:
                View.clearScreen();
                View.printOpcao("1. Criar Jogador");
                View.printOpcao("2. Criar Equipa");
                View.printOpcao("0. Voltar atrás");
                View.printPrompt("Choose Option");
                pieces = scanner.nextLine().split("\\s+");
                if (pieces.length == 1){
                    int number = getInt(pieces[0]);
                    if (number == 0) {
                        menu = 0;
                        break;
                    }
                    if (number == 1) {
                        Jogador jogador = askJogadores();
                        addJogador(jogador, titulosEquipas);
                        menu = 0;
                    }
                    if (number == 2){
                        boolean inserted = false;
                        do {
                            View.clearScreen();
                            View.printPrompt("Nome da Equipa");
                            String nomeEquipa = scanner.nextLine();
                            try{
                                model.addEquipa(makeEquipa(nomeEquipa));
                                titulosEquipas = model.nomesEquipasOrdenados();
                                View.printFrase("A equipa \" " + nomeEquipa +" \"foi adicionada");
                                pressAnyKeyToContinue();
                                menu = 0;
                                inserted = true;
                            }
                            catch (EquipaJaExisteException e){
                                View.printFrase(e.getMessage());
                                pressAnyKeyToContinue();
                            }
                        } while (!inserted);
                    }
                }
                else {
                    View.printFrase("Erro: Coloque apenas o número");
                    pressAnyKeyToContinue();
                }
                break;
            case 4:
                switch (submenu){
                    case 0:
                        View.printTitulo("Load from file");
                        View.printOpcao("1. Load Ficheiro de Texto");
                        View.printOpcao("2. Load Ficheiro Guardado");
                        View.printOpcao("0. Voltar Atrás");
                        View.printPrompt("Choose Option");
                        pieces = scanner.nextLine().split("\\s+");
                        if (pieces.length == 1){
                            opcao = getInt(pieces[0]);
                            if (opcao == 0){
                                menu = 0;
                                break;
                            }
                            else if (opcao == 1 || opcao == 2){
                                submenu = 1;
                            }
                            else{
                                View.printFrase("Erro: Opção inválida");
                                pressAnyKeyToContinue();
                            }
                        }
                        else {
                            View.printFrase("Erro: Coloque apenas o número");
                            pressAnyKeyToContinue();
                        }
                        break;
                    case 1:
                        View.printTitulo("Load Ficheiro");
                        View.printOpcao("0. Voltar atrás");
                        View.printPrompt("Nome do Ficheiro");
                        String str = scanner.nextLine();
                        if (str.equals("0"))submenu = 0;
                        else {
                            File temp = new File(str);
                            if (temp.exists()) {
                                if (opcao == 1) {
                                    try {
                                        model = Parser.parse(str);
                                        View.printFrase("Ficheiro carregado com sucesso");
                                    } catch (JogadorExistenteException | LinhaIncorretaException e) {
                                        View.printFrase("Erro na leitura do ficheiro");
                                    }
                                    menu = 0;
                                }
                                if (opcao == 2){
                                    model = model.readFromFile(str);
                                    menu = 0;
                                }
                            } else {
                                View.printFrase("ERRO - Ficheiro não existe");
                            }
                            pressAnyKeyToContinue();
                        }
                        break;
                }
                break;
            case 5:
                View.printTitulo("Save to file");
                View.printOpcao("0. Voltar atrás");
                View.printPrompt("Nome do Ficheiro");
                String str = scanner.nextLine();
                boolean write = false;
                if (str.equals("0")) menu = 0;
                else{
                    File temp = new File(str);
                    if (temp.exists()){
                        int number = -1;
                        do {
                            View.clearScreen();
                            View.printFrase("Já existe um ficheiro com esse nome. Quer dar overwrite?");
                            View.printOpcao("0.não");
                            View.printOpcao("1.Sim");
                            View.printPrompt("Resposta");
                            pieces = scanner.nextLine().split("\\s+");
                            if (pieces.length == 1) {
                                number = getInt(pieces[0]);
                                if (number == 1) write = true;
                                else if (number == 0) write = false;
                                else {
                                    View.printFrase("Erro: Opcão \"" + pieces[0] + "\" não existe");
                                    pressAnyKeyToContinue();
                                }
                            } else {
                                View.printFrase("Erro: Colque apenas o número");
                                pressAnyKeyToContinue();
                            }
                        }while (number != 0 && number != 1);
                    }
                    else write = true;
                    if (write){
                        View.clearScreen();
                        model.writeToFile(str);
                        pressAnyKeyToContinue();
                    }
                }
                break;
        }
        return out;
    }

    public static int askAtributo (String atributo){
        boolean valid = false;
        int valor = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            View.clearScreen();
            View.printPrompt(atributo);
            if (!scanner.hasNextInt()) {
                scanner.nextLine();
                View.printFrase("Insira um valor númerico.");
                pressAnyKeyToContinue();
            }
            else {
                valor = scanner.nextInt();
                if (valor >= 0 && valor <= 100)valid = true;
                else {
                    View.printFrase("Valor inválido");
                    pressAnyKeyToContinue();
                }
            }
        } while (!valid);
        return valor;
    }


    public static Equipa makeEquipa(String name){
        Equipa equipa = new Equipa();
        equipa.setName(name);
        return equipa;
    }

    public static Jogador askJogadores(){
        Scanner scanner = new Scanner(System.in);
        StringBuilder sb = new StringBuilder();
        View.clearScreen();
        View.printPrompt("Nome do Jogador");
        String nome = scanner.next();
        sb.append(nome).append(",");
        View.clearScreen();
        int num = askAtributo("Número da Camisola");
        sb.append(num).append(",");
        int posicao = -1;
        do {
            View.clearScreen();
            View.printTitulo("Posição:");
            View.printOpcao("0. Guarda-Redes");
            View.printOpcao("1. Defesa");
            View.printOpcao("2. Médio");
            View.printOpcao("3. Avançado");
            View.printPrompt("Resposta");
            if (scanner.hasNextInt()){
                posicao = scanner.nextInt();
            }
            else{
                scanner.next();
                View.printFrase("Erro: Opção inválida");
            }
        }while (!(posicao >= 0 && posicao <= 3));
        int velocidade = askAtributo("Velocidade");
        sb.append(velocidade).append(",");
        int resistencia = askAtributo("Resistência");
        sb.append(resistencia).append(",");
        int destreza = askAtributo("Destreza");
        sb.append(destreza).append(",");
        int impulsao = askAtributo("Impulsão");
        sb.append(impulsao).append(",");
        int jogoCabeca = askAtributo("Jogo de Cabeça");
        sb.append(jogoCabeca).append(",");
        int remate = askAtributo("Remate");
        sb.append(remate).append(",");
        int passe = askAtributo("Capacidade de Passe");
        sb.append(passe).append(",");
        int capDef = askAtributo("Capacidade Defensiva");
        sb.append(capDef).append(",");
        int posicionamento = askAtributo("Posicionamento");
        sb.append(posicionamento).append(",");
        if (posicao == 0) {
            int reflexos = askAtributo("Reflexos");
            sb.append(reflexos).append(",");;
            int elasticidade = askAtributo("Elasticidade");
            sb.append(elasticidade);
            System.out.println(sb.toString());
            return GuardaRedes.parseControlador(sb.toString());
        }
        if (posicao == 1) {
            int marcacao = askAtributo("Marcação");
            sb.append(marcacao);
            View.printOpcao("0. Defesa Central");
            View.printOpcao("1. Defesa Lateral");
            View.printPrompt("Choose Option");
            while (!scanner.hasNextInt()) {
                View.printFrase("Insira uma opção válida.");
            }
            int lateral = scanner.nextInt();
            if (lateral == 0) {
                return Defesa.parseControlador(sb.toString(), false);
            } else {
                int cruzamento = askAtributo("Cruzamento");
                sb.append(",").append(cruzamento);
                return Defesa.parseControlador(sb.toString(), true);
            }
        }
        if (posicao == 2) {
            int recuperacao = askAtributo("Recuperação de Bola: ");
            sb.append(recuperacao);
            View.printOpcao("0. Médio Central");
            View.printOpcao("1. Médio Lateral");
            while (!scanner.hasNextInt()) {
                View.printFrase("Insira uma opção válida.");
            }
            int lateral = scanner.nextInt();
            if (lateral == 0) {
                return Medio.parseControlador(sb.toString(), false);
            } else {
                int cruzamento = askAtributo("Cruzamento");
                sb.append(",").append(cruzamento);
                return Medio.parseControlador(sb.toString(), true);
            }
        }
        if (posicao == 3) {
            int finalizacao= askAtributo("Finalização");
            sb.append(finalizacao);
            View.printOpcao("0. Avançado Central");
            View.printOpcao("1. Avançado Lateral");
            while (!scanner.hasNextInt()) {
                View.printFrase("Insira uma opção válida.");
            }
            int lateral = scanner.nextInt();
            if (lateral == 0) {
                return Avancado.parseControlador(sb.toString(), false);
            } else {
                int cruzamento = askAtributo("Cruzamento");
                sb.append(",").append(cruzamento);
                return Avancado.parseControlador(sb.toString(), true);
            }
        }
        return null;
    }

    public void addJogador(Jogador jogador, List<String> titulosEquipas){
        View.printTitulo("Selecione Equipa para adicionar o seu Jogador");
        View.printSimpleOrganizedCollection(titulosEquipas,true);
        View.printPrompt("Choose Option");
        int opcao;
        String equipa = "";
        if ((opcao = selecionarEquipa(titulosEquipas)) != 0){
            equipa = titulosEquipas.get(opcao-1);
        }
        boolean inserted = false;
        while (!inserted){
            try{
                model.addJogador(jogador, equipa);
                inserted = true;
            }
            catch (JogadorExistenteException existenteException){
                View.printFrase("O número do jogador já existe na equipa");
                int num = askAtributo("Número novo do Jogador :");
                jogador.setId(num);
            }
        }
        View.printFrase("O Jogador foi adicionado à equipa");

    }

}