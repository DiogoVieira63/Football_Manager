import Exceptions.JogadorExistenteException;

import java.io.File;
import java.math.BigDecimal;
import java.sql.ClientInfoStatus;
import java.text.DecimalFormat;
import java.util.*;

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
        for (int i = 0;i < list.size();i++){
            Object obj = list.get(i);
            Map.Entry<Integer,String> entry = (Map.Entry<Integer,String>) obj;
            if (entry.getKey().equals(id)) return true;
        }
        return false;
    }


    public static int fracPartAsInt(double d, int digits){
        return (int)((d - ((int)d)) * Math.pow(10, digits));
    }


    public int selecionarEquipa(List<String> titulosEquipas){
        int opcao = 0;
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNextInt()) {
            opcao = scanner.nextInt();
            if (opcao >= 1 && opcao <= titulosEquipas.size()) {
                return opcao;
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

    public void selecionarTitulares (EquipaJogo equipaJogo){
        Scanner scanner = new Scanner(System.in);
        View.clearScreen();
        View.printTitulo("Esquema Tático");
        View.printOpcao("0. 4-3-3");
        View.printOpcao("1. 4-4-2");
        View.printPrompt("Choose Option");
        int number = -1;
        do {
            if (scanner.hasNextInt()){
                number = scanner.nextInt();
            }
            else{
                scanner.next();
                View.printFrase("Erro: Input inválido");
                pressAnyKeyToContinue();
            }
        }while (number != 0 && number != 1);
        List<Object> posicoes = equipaJogo.posicoes();
        for (int i = 1; i <= 11;i++){
            View.clearScreen();
            View.printPair(posicoes.get(i));
            Map.Entry<String,Boolean> entry = equipaJogo.infoPosicao(i-1);
            View.printPairOrganizedCollection(model.possiveisPosicao(equipaJogo.getIdEquipa(), entry.getKey(),entry.getValue()));
            View.printPrompt("Posição" + i);
            if (scanner.hasNextInt()){
                int jogador = scanner.nextInt();
                if (equipaJogo.isSuplente(jogador)){
                    equipaJogo.addTitular(jogador);
                }
            }
        }
    }





    @SuppressWarnings("unchecked")
    public void run () throws JogadorExistenteException {
        boolean out = false;
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;
        String equipa = "";
        int idJogador = 0;
        int submenu = 0;
        List<String> titulosEquipas = model.nomesEquipasOrdenados();
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
                        View.printFrase("ERRO - Coloque apenas o número");
                        pressAnyKeyToContinue();
                    }
                    break;
                case 1:
                    View.clearScreen();
                    View.printTitulo("Selecionar Equipa");
                    View.printSimpleOrganizedCollection(titulosEquipas);
                    View.printOpcao("0. Voltar atrás");
                    View.printPrompt("Choose Option");
                    if ((opcao = selecionarEquipa(titulosEquipas)) != 0){
                        equipa = titulosEquipas.get(opcao-1);
                        EquipaJogo equipaJogoCasa = new EquipaJogo(equipa,model.getNumeros(equipa));
                        submenu = 1;
                        selecionarTitulares(equipaJogoCasa);
                    }
                    break;
                case 2:
                    switch (submenu) {
                        case 0:
                            View.printTitulo("Selecionar Equipa");
                            View.printSimpleOrganizedCollection(titulosEquipas);
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
                            }
                            break;
                        case 2:
                            View.clearScreen();
                            View.printTitulo("Info Jogador");
                            View.printPairOrganizedCollection(model.infoJogador(equipa, idJogador));
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
                            View.printSimpleOrganizedCollection(aTransferir);
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
        Scanner scanner = new Scanner(System.in);
        View.printTitulo(atributo);
        while (!scanner.hasNextInt()){
            View.printFrase("Insira um valor númerico.");
            scanner.next();
        }
        int valor = scanner.nextInt();
        if (valor > 100 || valor < 0){
            View.printFrase("Insira um valor entre 0-100.");
            valor = -1;
        }
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
        View.printTitulo("Nome do Jogador: ");
        String nome = scanner.next();
        sb.append(nome).append(",");
        int num;
        do {
            num = askAtributo("Número da Camisola: ");
        }
        while (num == -1);
        sb.append(num).append(",");
        View.printTitulo("Posição:");
        View.printOpcao("0. Guarda-Redes");
        View.printOpcao("1. Defesa");
        View.printOpcao("2. Médio");
        View.printOpcao("3. Avançado");
        while (!scanner.hasNextInt()) {
            View.printFrase("Insira uma opção válida.");
        }
        int posicao = scanner.nextInt();
        int velocidade;
        do {
            velocidade = askAtributo("Velocidade: ");
        }
        while (velocidade == -1);
        sb.append(velocidade).append(",");
        int resistencia;
        do {
            resistencia = askAtributo("Resistência: ");
        }
        while (resistencia == -1);
        sb.append(resistencia).append(",");
        int destreza;
        do {
            destreza = askAtributo("Destreza: ");
        }while (destreza == -1);
        sb.append(destreza).append(",");
        int impulsao;
        do {
            impulsao = askAtributo("Impulsão: ");
        }while (impulsao == -1);
        sb.append(impulsao).append(",");
        int jogoCabeca;
        do {
            jogoCabeca = askAtributo("Jogo de Cabeça: ");
        }while (jogoCabeca == -1);
        sb.append(jogoCabeca).append(",");
        int remate;
        do {
            remate = askAtributo("Remate: ");
        }while (remate == -1);
        sb.append(remate).append(",");
        int passe;
        do {
            passe = askAtributo("Capacidade de Passe: ");
        }while (passe == -1);
        sb.append(passe).append(",");
        int capDef;
        do {
            capDef = askAtributo("Capacidade Defensiva: ");
        }while (capDef == -1);
        sb.append(capDef).append(",");
        int posicionamento;
        do {
            posicionamento = askAtributo("Posicionamento: ");
        }while (posicionamento == -1);
        sb.append(posicionamento).append(",");
        if (posicao == 0) {
            int reflexos;
            do {
                reflexos = askAtributo("Reflexos: ");
            } while (reflexos == -1);
            sb.append(reflexos).append(",");;
            int elasticidade;
            do {
                elasticidade = askAtributo("Elasticidade: ");
            } while (elasticidade == -1);
            sb.append(elasticidade);
            return GuardaRedes.parseControlador(sb.toString());
        }
        if (posicao == 1) {
            int marcacao;
            do {
                marcacao = askAtributo("Marcação: ");
            } while (marcacao == -1);
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
                int cruzamento;
                do {
                    cruzamento = askAtributo("Cruzamento: ");
                }while (cruzamento == -1);
                sb.append(",").append(cruzamento);
                return Defesa.parseControlador(sb.toString(), true);
            }
        }
        if (posicao == 2) {
            int recuperacao;
            do {
                recuperacao = askAtributo("Recuperação de Bola: ");
            }while (recuperacao == -1);
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
                int cruzamento;
                do {
                    cruzamento = askAtributo("Cruzamento: ");
                }while (cruzamento == -1);
                sb.append(",").append(cruzamento);
                return Medio.parseControlador(sb.toString(), true);
            }
        }
        if (posicao == 3) {
            int finalizacao;
            do {
                finalizacao = askAtributo("Finalização: ");
            }while (finalizacao == -1);
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
                int cruzamento;
                do {
                    cruzamento = askAtributo("Cruzamento: ");
                }while (cruzamento == -1);
                sb.append(",").append(cruzamento);
                return Avancado.parseControlador(sb.toString(), true);
            }
        }
        return null;
    }

    public void addJogador(Jogador jogador, List<String> titulosEquipas) throws JogadorExistenteException {
        View.printTitulo("Selecione Equipa para adicionar o seu Jogador");
        View.printSimpleOrganizedCollection(titulosEquipas);
        View.printPrompt("Choose Option");
        int opcao;
        String equipa = "";
        if ((opcao = selecionarEquipa(titulosEquipas)) != 0){
            equipa = titulosEquipas.get(opcao-1);
        }
        try{
            model.addJogador(jogador, equipa);
        }
        catch (JogadorExistenteException existenteException){
            View.printFrase("O número do jogador já existe na equipa");
            int num;
            do {
                num = askAtributo("Número novo do Jogador :");
            }while (num == -1);
            jogador.setId(num);
            model.addJogador(jogador, equipa);
        }
        View.printFrase("O Jogador foi adicionado á equipa");
    }

}
