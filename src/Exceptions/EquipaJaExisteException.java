package Exceptions;

import java.io.Serializable;

public class EquipaJaExisteException extends Exception{

    public EquipaJaExisteException(){
        super();
    }

    public EquipaJaExisteException(String id){
        super(id);
    }
}
