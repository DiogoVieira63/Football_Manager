import Exceptions.JogadorExistenteException;
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
    private View view;
    private int menu;

    public Controller (Model model, View view){
        this.model = model;
        this.view = view;
        this.menu = 0;
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
        int opcao = 0;
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            opcao = scanner.nextInt();
            if (opcao >= 1 && opcao <= titulosEquipas.size()) {
                if (!model.isEmptyEquipa(titulosEquipas.get(opcao-1))) return opcao;
                else {
                    View.printFrase("A equipa não contém jogadores");
                }
            } else if (opcao == 0) {
                menu = 0;
            } else {
                View.printFrase("ERRO - Opção inválida");
                pressAnyKeyToContinue();
            }
        } else {
            scanner.next();
            View.printFrase("ERRO -Coloque apenas o número");
            pressAnyKeyToContinue();
        }
        return 0;
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

    public boolean selecionarTitulares (EquipaJogo equipaJogo){
        Scanner scanner = new Scanner(System.in);
        int number = -1;
        do {
            View.clearScreen();
            View.printTitulo("Esquema Tático");
            View.printOpcao("0. 4-3-3");
            View.printOpcao("1. 4-4-2");
            View.printPrompt("Choose Option");
            if (scanner.hasNextInt()){
                number = scanner.nextInt();
            }
            else{
                scanner.next();
                View.printFrase("Erro: Input inválido");
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
                    View.printFrase(e.getMessage());
                    menu = 0;
                    return false;
                }
                View.printPairOrganizedCollection(possiveis);
                View.printPrompt("Posição" + i);
                if (scanner.hasNextInt()) {
                    int jogador = scanner.nextInt();
                    if (isPossible(possiveis,jogador)) {
                        equipaJogo.addTitular(jogador);
                        inserted = true;
                    }
                }
                else {
                    scanner.next();
                    View.printFrase("Erro: Input inválido");
                    pressAnyKeyToContinue();
                }
            }while (!inserted);
        }
        do {
            View.clearScreen();
            View.printTitulo("Nr de Substituições (0-3)");

            View.printPrompt("Resposta");
            if (scanner.hasNextInt()){
                number = scanner.nextInt();
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
                sair = askInt();
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
                }
                catch (NaoHaJogadorPosicaoException e){
                    View.clearScreen();
                }
                View.printPairOrganizedCollection(possiveis);
                View.printPrompt("Jogador a entrar");
                entrar = askInt();
            } while (!isPossible(possiveis,entrar) || equipaJogo.inSubstituicao(entrar));
            equipaJogo.addSubstituicao(sair,entrar);
        }
        return true;
    }





    @SuppressWarnings("unchecked")
    public void run ()throws JogadorExistenteException{
        boolean out = false;
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        String equipa = "";
        int idJogador = 0;
        int submenu = 0;
        List<String> titulosEquipas = model.nomesEquipasOrdenados();
        List<EquipaJogo> equipaJogoList = new ArrayList<>();
        while (!out){
            View.clearScreen();
            switch ((int)menu){
                case 0:
                    view.menuPrincipal();
                    if (scanner.hasNextInt()) {
                        opcao = scanner.nextInt();
                        if (opcao >= 1 && opcao <= 5) {
                            menu = opcao;
                        }
                        else if (opcao == 6) out = true;
                        else {
                            View.printFrase("ERRO - Opção inválida");
                            pressAnyKeyToContinue();
                        }
                    }
                    else {
                        scanner.next();
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
                            if (scanner.hasNextInt()) {
                                int number = scanner.nextInt();
                                if (containsId(number, list)) {
                                    idJogador = number;
                                    submenu = 2;
                                }
                            } else {
                                String string = scanner.next();
                                if (string.equals("z")) {
                                    submenu = 0;
                                }
                                if (string.equals("r")){
                                    submenu = 4;
                                }
                            }
                            break;
                        case 2:
                            View.clearScreen();
                            View.printTitulo("Info Jogador");
                            View.printSimpleOrganizedCollection(model.infoJogador(equipa, idJogador),false);
                            View.printOpcao("0. Voltar Atrás");
                            View.printOpcao("1. Transferir Jogador");
                            View.printPrompt("Choose Option");
                            if (scanner.hasNextInt()){
                                int number = scanner.nextInt();
                                if (number == 0) submenu = 1;
                                if (number == 1) submenu = 3;

                            }
                            else
                            {
                                scanner.next();
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
                            if (scanner.hasNextInt()) {
                                opcao = scanner.nextInt();
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
                            } else {
                                scanner.next();
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
                    View.printOpcao("0. Jogador");
                    View.printOpcao("1. Equipa");
                    View.printPrompt("Choose Option");
                    String str = scanner.next();
                    if (str.equals("0")) {
                        Jogador jogador = askJogadores();
                        addJogador(jogador, titulosEquipas);
                        menu = 0;
                    }
                    if (str.equals("1")){
                        View.clearScreen();
                        View.printFrase("Nome da Equipa: ");
                        String nomeEquipa = scanner.next();
                        while (model.getCatalogoEquipas().getCatalogoEquipas().containsKey(nomeEquipa)){
                            View.printFrase("Já existe uma equipa com esse nome. Insira novamente: ");
                            nomeEquipa = askEquipa();
                        }
                        Equipa equipa1 = makeEquipa(nomeEquipa);
                        model.addEquipa(equipa1);
                        titulosEquipas = model.nomesEquipasOrdenados();
                        View.printTitulo("A sua equipa foi adicionada");
                        menu = 0;
                    }
                    break;
                case 4:
                    View.printTitulo("Load from file");
                    View.printPrompt("Nome do Ficheiro");
                    str = scanner.next();
                    if (str.equals("0"))menu = 0;
                    else {
                        File temp = new File(str);
                        if (temp.exists()) {
                            model = model.readFromFile(str);
                            menu = 0;
                        } else {
                            View.printFrase("ERRO - Ficheiro não existe");
                        }
                        pressAnyKeyToContinue();
                    }
                    break;
                case 5:
                    View.printTitulo("Save to file");
                    View.printPrompt("Nome do Ficheiro");
                    str = scanner.next();
                    if (str.equals("0")) menu = 0;
                    else{
                        File temp = new File(str);
                        if (temp.exists()){
                            View.printFrase("Já existe um ficheiro com esse nome. Quer dar overwrite?");
                            View.printFrase("1 para sim | 0 para não");
                            View.printPrompt("Resposta");
                            while (scanner.hasNextInt()){
                                opcao = scanner.nextInt();
                                if (opcao == 1) {
                                    model.writeToFile(str);
                                    menu = 0;
                                    pressAnyKeyToContinue();
                                    break;
                                }
                                else break;
                            }

                        }
                        else {
                            model.writeToFile(str);
                            menu = 0;
                            pressAnyKeyToContinue();
                        }
                    }
                    break;
            }

        }
    }

    public static int askAtributo (String atributo){
        boolean valid = false;
        int valor = 0;
        Scanner scanner = new Scanner(System.in);
        do {
            View.clearScreen();
            View.printPrompt(atributo);
            if (!scanner.hasNextInt()) {
                scanner.next();
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

    public static String askEquipa(){
        Scanner scanner = new Scanner(System.in);
        View.printTitulo("Nome da Equipa: ");
        String equipa = scanner.next();
        return equipa;
    }

    public static Equipa makeEquipa(String name) throws JogadorExistenteException {
        Scanner scanner = new Scanner(System.in);
        View.printTitulo("Quantos jogadores pretende adcionar? ");
        while(!scanner.hasNextInt()){
            View.printFrase("Insira um valor numérico.");
            scanner.next();
        }
        int num = scanner.nextInt();
        Equipa equipa = new Equipa();
        equipa.setName(name);
        for (int i = 0; i <num; i++){
            View.printFrase("Jogador " + i +": ");
            Jogador player = askJogadores();
            equipa.addJogador(player);
        }
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

    public void addJogador(Jogador jogador, List<String> titulosEquipas) {
        View.printTitulo("Selecione Equipa para adicionar o seu Jogador");
        View.printSimpleOrganizedCollection(titulosEquipas,true);
        View.printPrompt("Choose Option");
        int opcao;
        String equipa = "";
        if ((opcao = selecionarEquipa(titulosEquipas)) != 0){
            equipa = titulosEquipas.get(opcao-1);
        }
        boolean adicionado = false;
        while (!adicionado) {
            try {
                model.addJogador(jogador, equipa);
                adicionado = true;
            } catch (JogadorExistenteException existenteException) {
                View.printFrase("O número do jogador já existe na equipa");
                pressAnyKeyToContinue();
                int num = askAtributo("Número novo do Jogador");
                jogador.setId(num);
            }
        }
        View.printFrase("O Jogador foi adicionado à equipa");
    }

}