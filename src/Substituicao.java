import java.io.Serializable;
import java.util.Comparator;

public class Substituicao implements Serializable {
    private static final long serialVersionUID = 2335398657458190543L;
    private int out;
    private int in;
    private boolean done;

    public Substituicao (int out, int in){
        this.out = out;
        this.in = in;
        this.done = false;
    }

    public int getIn() {
        return in;
    }

    public int getOut() {
        return out;
    }

    public boolean getDone (){
        return done;
    }
    public void setDone (boolean done){
        this.done = done;
    }
}
