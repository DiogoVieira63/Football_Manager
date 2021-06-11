import Exceptions.JogadorExistenteException;

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
                        StringBuilder sb = new StringBuilder();
                        View.printTitulo("Nome do Jogador: ");
                        String nome = scanner.next();
                        sb.append(nome).append(",");
                        View.printTitulo("Número da Camisola: ");
                        while (!scanner.hasNextInt()) {
                            View.printFrase("Insira um número válido.");
                        }
                        int num = scanner.nextInt();
                        sb.append(num).append(",");;
                        View.printTitulo("Posição:");
                        View.printOpcao("0. Guarda-Redes");
                        View.printOpcao("1. Defesa");
                        View.printOpcao("2. Médio");
                        View.printOpcao("3. Avançado");
                        while (!scanner.hasNextInt()) {
                            View.printFrase("Insira uma opção válida.");
                        }
                        int posicao = scanner.nextInt();
                        int valor = askAtributo("Velocidade: ");
                        while (valor == 0) {
                            valor = askAtributo("Velocidade: ");
                        }
                        sb.append(valor).append(",");
                        int valor2 = askAtributo("Resistência: ");
                        while (valor2 == 0) {
                            valor2 = askAtributo("Resistência: ");
                        }
                        sb.append(valor2).append(",");
                        int valor3 = askAtributo("Destreza: ");
                        while (valor3 == 0) {
                            valor3 = askAtributo("Destreza: ");
                        }
                        sb.append(valor3).append(",");
                        int valor4 = askAtributo("Impulsão: ");
                        while (valor4 == 0) {
                            valor4 = askAtributo("Impulsão: ");
                        }
                        sb.append(valor4).append(",");
                        int valor5 = askAtributo("Jogo de Cabeça: ");
                        while (valor5 == 0) {
                            valor5 = askAtributo("Jogo de Cabeça: ");
                        }
                        sb.append(valor5).append(",");
                        int valor6 = askAtributo("Remate: ");
                        while (valor6 == 0) {
                            valor6 = askAtributo("Remate: ");
                        }
                        sb.append(valor6).append(",");
                        int valor7 = askAtributo("Capacidade de Passe: ");
                        while (valor7 == 0) {
                            valor7 = askAtributo("Capacidade de Passe: ");
                        }
                        sb.append(valor7).append(",");
                        int valor9 = askAtributo("Capacidade Defensiva: ");
                        while (valor9 == 0) {
                            valor9 = askAtributo("Capacidade Defensiva: ");
                        }
                        sb.append(valor9).append(",");
                        int valor10 = askAtributo("Motivação: ");
                        while (valor10 == 0) {
                            valor10 = askAtributo("Motivação: ");
                        }
                        sb.append(valor10).append(",");
                        int valor11 = askAtributo("Posicionamento: ");
                        while (valor11 == 0) {
                            valor11 = askAtributo("Posicionamento: ");
                        }
                        sb.append(valor11).append(",");
                        if (posicao == 0) {
                            int valor12 = askAtributo("Reflexos: ");
                            while (valor12 == 0) {
                                valor12 = askAtributo("Reflexos: ");
                            }
                            sb.append(valor12).append(",");;
                            int valor8 = askAtributo("Elasticidade: ");
                            while (valor8 == 0) {
                                valor8 = askAtributo("Elasticidade: ");
                            }
                            sb.append(valor8);
                            GuardaRedes guardaRedes = GuardaRedes.parseControlador(sb.toString());
                            addJogador(guardaRedes, titulosEquipas);
                        }
                        if (posicao == 1) {
                            int marcacao = askAtributo("Marcação: ");
                            while (marcacao == 0) {
                                marcacao = askAtributo("Marcação: ");
                            }
                            sb.append(marcacao);
                            View.printOpcao("0. Defesa Normal");
                            View.printOpcao("1. Defesa Lateral");
                            View.printPrompt("Choose Option");
                            while (!scanner.hasNextInt()) {
                                View.printFrase("Insira uma opção válida.");
                            }
                            int lateral = scanner.nextInt();
                            if (lateral == 0) {
                                Defesa defesa = Defesa.parseControlador(sb.toString(), false);
                            } else {
                                int cruzamento = askAtributo("Cruza: ");
                                while (cruzamento == 0) {
                                    cruzamento = askAtributo("Marcação: ");
                                }
                                sb.append(",").append(cruzamento);
                                Defesa defesaLateral = Defesa.parseControlador(sb.toString(), true);
                            }
                        }
                        if (posicao == 2) {
                            int recuperacao = askAtributo("Recuperação de Bola: ");
                            while (recuperacao == 0) {
                                recuperacao = askAtributo("Recuperação de Bola: ");
                            }
                            sb.append(recuperacao);
                            View.printOpcao("0. Médio Normal");
                            View.printOpcao("1. Médio Lateral");
                            while (!scanner.hasNextInt()) {
                                View.printFrase("Insira uma opção válida.");
                            }
                            int lateral = scanner.nextInt();
                            if (lateral == 0) {
                                Medio medio = Medio.parseControlador(sb.toString(), false);
                            } else {
                                int cruzamento = askAtributo("Cruza: ");
                                while (cruzamento == 0) {
                                    cruzamento = askAtributo("Marcação: ");
                                }
                                sb.append(",").append(cruzamento);
                                Medio medioLateral = Medio.parseControlador(sb.toString(), true);
                            }
                        }
                        if (posicao == 3) {
                            int finalizacao = askAtributo("Finalização: ");
                            while (finalizacao == 0) {
                                finalizacao = askAtributo("Finalização: ");
                            }
                            sb.append(finalizacao);
                            View.printOpcao("0. Avançado Normal");
                            View.printOpcao("1. Avançado Lateral");
                            while (!scanner.hasNextInt()) {
                                View.printFrase("Insira uma opção válida.");
                            }
                            int lateral = scanner.nextInt();
                            if (lateral == 0) {
                                Avancado avancado = Avancado.parseControlador(sb.toString(), false);
                            } else {
                                int cruzamento = askAtributo("Cruza: ");
                                while (cruzamento == 0) {
                                    cruzamento = askAtributo("Marcação: ");
                                }
                                sb.append(",").append(cruzamento);
                                Avancado avancadoLateral = Avancado.parseControlador(sb.toString(), true);
                                pressAnyKeyToContinue();
                            }
                        }
                    }
                    //add equipa
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
        }
        int valor = scanner.nextInt();
        if (valor > 100 || valor < 0){
            View.printFrase("Insira um valor entre 0-100.");
            valor = 0;
        }
        return valor;
    }


    public void addJogador(Jogador jogador, List<String> titulosEquipas) throws JogadorExistenteException {
        View.printTitulo("Selecionar Equipa");
        View.printSimpleOrganizedCollection(titulosEquipas);
        View.printPrompt("Choose Option");
        int opcao;
        String equipa = "";
        if ((opcao = selecionarEquipa(titulosEquipas)) != 0){
            equipa = titulosEquipas.get(opcao-1);
        }
        model.addJogador(jogador, equipa);
        View.printFrase("O Jogador foi adicionado á equipa");
    }

}
