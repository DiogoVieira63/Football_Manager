package Exceptions;

public class EquipaSemJogadoresException extends Exception{

    public EquipaSemJogadoresException(){
        super();
    }

    public EquipaSemJogadoresException(String id){
        super(id);
    }
}
