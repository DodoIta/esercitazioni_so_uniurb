package ambulatorioospedale;

import java.util.Random;

public class Persona extends Thread{
    // attributi interni
    private Ambulatorio ambulatorio;
    private Random rnd;
    private int index;
    
    // costruttore
    public Persona (Ambulatorio a, int i, String name){
        super(name+"_"+i);
        this.index = i;
        this.ambulatorio = a;
        this.rnd = new Random();
    }
    
    // metodo run del thread
    @Override
    public void run(){
        // simulo l'arrivo in tempo casuale all'ambulatorio
        try{
            Thread.sleep(this.rnd.nextInt(700)+1);
        }catch(InterruptedException e){
            System.out.println(e);
        }
        
        // invoca mettitiInCoda
        this.ambulatorio.mettitiInCoda(this);
        
        // invoco il metodo ottieniPrestazione per simulare il tempo passato in ambulatorio
        this.ambulatorio.ottieniPrestazione(this);
        
        // notifico l'uscita dall'ambulatorio
        this.ambulatorio.esci(this);
        
        System.out.println(this.getName()+" termina l'esecuzione....");
    }
}
