package ambulatorioospedale;

public class Informatore extends Persona {
    // definisco il costruttore che soddisfi quello di mio padre
    public Informatore (Ambulatorio a, int i, String name){
        super(a,i,name);    /* questa invocazione soddisfa il costruttore di mio padre */
    }
}
