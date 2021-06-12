package Exceptions;

public class JogadorExistenteException extends Exception{
    public JogadorExistenteException(){
        super();
    }

    public JogadorExistenteException(String id){
        super(id);
    }
}
