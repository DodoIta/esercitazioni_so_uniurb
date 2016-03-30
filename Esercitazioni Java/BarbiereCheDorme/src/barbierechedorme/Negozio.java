/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbierechedorme;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

/* Mascetti Flavio */

public class Negozio {
    /* Oggetto condiviso tra i thread barbiere ed i thread cliente */
    
    // Attributi funzionali
    private int sedieDisponibili;
    private int sedieTotali;
    
    // Attributi per la sincronizzazione
    ReadLock lock;
    /* lock per la mutua esclusione per proteggere la variabile condivisa
       sedieDisponibili */
    
    /* Semaforo per la sospensione del barbiere in attesa dei clienti */
    Semaphore clienti;
    /* Semaforo per la sospensione dei clienti in attesa sulle sedie */
    Semaphore barbiere;
    
    // Costruttore del negozio
    public Negozio (int n){
        this.sedieTotali = n;
        this.sedieDisponibili = n;
        
        // inizializzo gli attributi di sincronizzazione
        this.lock = new ReentrantReadWriteLock().readLock();
        this.barbiere = new Semaphore(0,true); /* true sveglia in ordine FIFO */
        this.clienti = new Semaphore(0); /* numero di permessi uguale a 0 per
                                            sospendere immediatamente il
                                            barbiere fino all'arrivo dei clienti
                                         */
    }// end metodo costruttore
    
    // Implementazione del metodo per servire i clienti da parte del barbiere
    /* metodo bloccante che inoltra le eccezzioni all'invocante per gestire la
       terminazione differita */
    public void serviCliente() throws InterruptedException {
        /* il barbiere acquisisce un permesso sul semaforo clienti per poter
           eseguire */
        this.clienti.acquire(); /* il barbiere di ferma subito senza
                                   occupare la CPU */
        
        /* se sono qui significa che un cliente e' arrivato e mi ha svegliato
           rilasciando un permesso sul semaforo */
        // inizio a gestire il cliente
        System.out.println("Il Barbiere serve un cliente...");
        /* simulo il tempo necessario a tagliare i capelli sospendendo il
           barbiere per un tempo costante */
        Thread.sleep (15);
        
        /* libera una sedia e fa uscire il cliente al quale ha appena tagliato
           i capelli dal negozio */
        /* acquisisco il lock per la mutua esclusione per poter modificare la
           variabile condivisa */
        
        this.lock.lock();
        try{
            // incremento il numero di sedie disponibili
            this.sedieDisponibili++;
            /* libero il cliente dal semaforo barbiere in ordine FIFO */
            this.barbiere.release();
            
        }finally{
            this.lock.unlock();
        }
    }//end metodo serviCliente
    
    //metodo usato dai clienti per entrare in negozio e farsi tagliare i capelli
    public boolean entraNegozio(Cliente c) {
        boolean ret = true;
        // devo controllare se ci sono sedie disponibili
        /* devo acquisire il lock per la mutua esclusione poiché acceso a
           variabili condivise */
        
        this.lock.lock();
        
        try{
            // siamo in mutua esclusione
            if (this.sedieDisponibili > 0){
                // posso attendere in coda per farmi tagliare i capelli
                System.out.println("Il cliente "+
                                   c.getIndex()+
                                   " entra in attesa");
                // decremento il numero di sedie disponibili
                this.sedieDisponibili++;
                
                // sveglio il barbiere se stava dormendo
                this.clienti.release();
                
                /* 
                   ora mi metto in attesa del taglio di capelli per opera del
                   barbiere
                */
                
                /* Libero la sezione critica */
                this.lock.unlock();
                
                this.barbiere.acquire();
            }
            else{
                // esco senza farmi tagliare i capelli
                ret = false;
                this.lock.unlock();
            }
        }catch (InterruptedException e){
            System.out.println(e);
        }
        return ret;
    } // end metodo entraNegozio
}
