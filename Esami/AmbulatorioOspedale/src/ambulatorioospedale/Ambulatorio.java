package ambulatorioospedale;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

// oggetto condiviso tra i thread paziente ed informatore
public class Ambulatorio {
    
    // attributi interni di sincronizzazione
    private ReentrantLock lock; // lock per la mutua esclusione
    
    // coda di attesa per i pazienti
    private Condition sospendiPazienti; // coda di attesa implicita FIFO
    
    // coda di attesa per gli informatori
    private Condition sospendiInformatori; // coda di attesa implicita FIFO
    
    // attributi funzionali
    private boolean occupato;// indica se l'ambulatorio sta servendo una persona
    
    private int pazientiServiti; /* contatore per gestire interlacciamenti
                                    3 Pazienti - 1 Informatore             */
    private int pazientiInCoda;
    
    private int informatoriInCoda;
    
    // metodo costruttore
    public Ambulatorio(){
        this.lock                   = new ReentrantLock();
        this.sospendiPazienti       = this.lock.newCondition();
        this.sospendiInformatori    = this.lock.newCondition();
        this.pazientiServiti        = 0;
        this.pazientiInCoda         = 0;
        this.informatoriInCoda      = 0;
        this.occupato               = false;
    }
    
    // metodo per entrare in coda
    public void mettitiInCoda (Persona p){
        // come faccio a capire se p é di tipo informatore o paziente?
        // Distinzione sul thread invocante
        if (p instanceof Paziente){   // l'invocante e' un paziente
            System.out.println("Il paziente "+p.getName()+" si mette in coda");
            
                // INIZIO SEZIONE CRITICA
            this.lock.lock();
            
            try{
                this.pazientiInCoda++;
                
                // controllo se l'ambulatorio e' occupato
                while(this.occupato)
                    this.sospendiPazienti.await();
                /*  se sono qui significa che l'ambulatorio non era occupato
                    oppure che sono stato svegliato dopo la sospensione */
                // entro in ambulatorio
                this.occupato = true;
                this.pazientiServiti++;
                this.pazientiInCoda --;
                
            }catch (InterruptedException e){
                System.out.println(e);
            }finally{
                this.lock.unlock();
            }
                // FINE SEZIONE CRITICA
        }
        else{                           // l'invocante e' un informatore
            System.out.println("L'informatore "+p.getName()+" si mette in coda");
            
                // INIZIO SEZIONE CRITICA
            this.lock.lock();
            
            try{
                this.informatoriInCoda ++;
                
                // controllo se l'ambulatorio e' occupato
                while(this.occupato)
                    this.sospendiInformatori.await();
                
                // entro in ambulatorio
                this.occupato = true;
                this.informatoriInCoda --;
                
            }catch (InterruptedException e){
                System.out.println(e);
            }finally{
                this.lock.unlock();
            }
                // FINE SEZIONE CRITICA
        }
    }// end mettiInCoda
    
    public void ottieniPrestazione (Persona p){
        System.out.println(p.getName()+" entra in ambulatorio...");
        
        // sospendo la persona per 300ms
        try{
            Thread.sleep(300);
        }catch(InterruptedException e){
            System.out.println(e);
        }
    } // end metodo ottieniPrestazione
    
    // metodo esci invocato dalle persone per notificare la loro uscita dall'ambulatorio
    public void esci(Persona p){
        /*  acquisisco il lock per la mutua esclusione poiché andrò ad accedere
            alle variabili condivise */
        this.lock.lock();
        try{
            // metto la variabile occupato a false
            this.occupato = false;
            
            // controllo se sono stati serviti 3 pazienti consecutivamente
            if (this.pazientiServiti == 3){
                this.pazientiServiti = 0;
                // sveglio (se esiste) un informatore dalla sua coda
                if (this.informatoriInCoda > 0)
                    this.sospendiInformatori.signal();
                else // sveglio un paziente
                    this.sospendiPazienti.signal();
            } else{
                /*  se ci sono pazienti in coda sveglio un paziente altrimenti
                    sveglio un informatore */
                if (this.pazientiInCoda > 0)
                    this.sospendiPazienti.signal();
                else
                    this.sospendiInformatori.signal();
            }
            System.out.println(p.getName()+" lascia l'ambulatorio...");
        }finally{
            this.lock.unlock();
        }
    }// end esci()
}
