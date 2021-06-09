import java.io.File;
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

    private void pressAnyKeyToContinue()
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

    @SuppressWarnings("unchecked")
    public void run (){
        boolean out = false;
        Scanner scanner = new Scanner(System.in);
        int opcao;
        while (!out){
            View.clearScreen();
            switch (menu){
                case 0:
                    view.menuPrincipal();
                    if (scanner.hasNextInt()) {
                        opcao = scanner.nextInt();
                        if (opcao >= 1 && opcao <= 4) menu = opcao;
                        else if (opcao == 5) out = true;
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
                case 2:
                    View.printTitulo("Selecionar Equipa");
                    List<String> titulosEquipas = model.nomesEquipasOrdenados();
                    View.printSimpleOrganizedCollection(titulosEquipas);
                    view.printOpcao("0. Voltar atrás");
                    View.printPrompt("Choose Option");
                    if (scanner.hasNextInt()) {
                        opcao = scanner.nextInt();
                        if (opcao >= 1 && opcao <= titulosEquipas.size()) {
                            View.clearScreen();
                            View.printTitulo("Selecionar Jogador");
                            String equipa = titulosEquipas.get(opcao-1);
                            List <Object> list = model.nomesJogadoresOrdenado(equipa);
                            View.printPairOrganizedCollection(list);
                            view.printOpcao("0. Voltar atrás");
                            View.printPrompt("Choose Option");
                            while (true) {
                                if (scanner.hasNextInt()) {
                                    int number = scanner.nextInt();
                                    if(containsId(number,list)){
                                        View.clearScreen();
                                        View.printPairOrganizedCollection(model.infoJogador(equipa,number));
                                    }

                                }
                            }

                        }
                        else if (opcao == 0) menu = 0;
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
                case 3:
                    View.printTitulo("Load from file");
                    View.printPrompt("Nome do Ficheiro");
                    String str = scanner.next();
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
                case 4:
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
}
