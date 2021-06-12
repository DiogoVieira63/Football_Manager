import javax.management.ObjectName;
import java.io.ObjectStreamException;
import java.util.Collection;
import java.util.Map;
import java.util.Scanner;
import java.util.function.IntBinaryOperator;

public class View {
    private int menu;

    private static int largura = 40;


    public View (){
        menu = 0;
    }

    public static String centerString (int width, String s) {
        return String.format("%-" + width  + "s", String.format("%" + (s.length() + (width - s.length()) / 2) + "s", s));
    }


    public static void printOpcao (String string){
        System.out.println("\n"+centerString(largura,string));
    }

    public static void printTitulo (String str){
        System.out.printf("%s\n\n",centerString(largura,str));
    }

    public static void printPrompt (String str){
        System.out.print("\n"+ str + ":");
    }

    public static void menuPrincipal (){
        printTitulo("FOOTBALL MANAGER");
        printOpcao("1.Simular Jogo");
        printOpcao("2.Catálogo de Equipas");
        printOpcao("3.Criar Equipa/Jogador");
        printOpcao("4.Load File");
        printOpcao("5.Save File");
        printOpcao("6.Exit");
        printPrompt("Choose Option");
    }

    public static void printFrase (String str){
        System.out.println(centerString(largura,str));
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void printSimpleOrganizedCollection(Collection<String> collection,boolean withNumber){
        int number = 1;
        for(String str: collection){
            if (withNumber)System.out.println(centerString(largura,number +":"+ str));
            else System.out.println(centerString(largura, str));
            number++;
        }
    }

    @SuppressWarnings("unchecked")
    public static void printPairOrganizedCollection (Collection <Object> collection){
        for (Object obj : collection){
            Map.Entry<Integer,Object> entry = (Map.Entry<Integer,Object>) obj;
            if (entry.getKey() != -1)System.out.println(centerString(largura,entry.getKey() + " " + entry.getValue()));
            else System.out.println(centerString(largura, " " + entry.getValue()));
        }
    }

    @SuppressWarnings("unchecked")
    public static void printPair (Object obj){
        Map.Entry<Object,Object> entry = (Map.Entry<Object,Object>) obj;
        System.out.println(centerString(largura,entry.getKey() + " " + entry.getValue()));
    }




}
