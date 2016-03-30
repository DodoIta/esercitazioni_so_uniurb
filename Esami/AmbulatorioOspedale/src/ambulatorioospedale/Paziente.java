package ambulatorioospedale;

public class Paziente extends Persona {
    // definisco il costruttore che soddisfi quello di mio padre
    public Paziente (Ambulatorio a, int i, String name){
        super(a,i,name);    /* questa invocazione soddisfa il costruttore di mio padre */
    }
}
