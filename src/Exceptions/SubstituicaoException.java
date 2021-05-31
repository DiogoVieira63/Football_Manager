package Exceptions;

public class SubstituicaoException extends Exception {
    public SubstituicaoException(){
        super();
    }

    public SubstituicaoException(String id){
        super(id);
    }
}
