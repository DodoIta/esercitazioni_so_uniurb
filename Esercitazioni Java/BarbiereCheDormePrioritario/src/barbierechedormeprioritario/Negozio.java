/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbierechedormeprioritario;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author flavio
 */
public class Negozio {
    // attributi interni di sincronizzazione
    
    // lock per la mutua esclusione
    private ReentrantLock lock;
    
    // semaforo per sospendere il barbiere
    private Semaphore clienti;
    
    // lock per l'accesso in mutua esclusione alla stringa di stampa
    private ReentrantLock stringLock;
    
    // attributi funzionali
    private int sedieTotali;
    private int sedieDisponibili;
    
    // lista per contenere i riferimentiai clienti entranti in negozio
    LinkedList<Cliente> codaClienti;
    
    // stringa di stampa sulla quale appendere l'output di tutti i thread
    private String output;
    
    
    // costruttore del negozio
    public Negozio (int n){
        this.sedieTotali = n;
        this.sedieDisponibili = n;
        this.output = "";
        this.codaClienti = new LinkedList<Cliente>();
        
        // attributi di sincronizzazione
        this.lock = new ReentrantLock();
        this.stringLock = new ReentrantLock();
        this.clienti = new Semaphore(0);
    }
    
    public void println (String s){
        // acquisire il lock per la mutua esclusione
        this.stringLock.lock();
        
        try{
            // appendo la stringa s al contenuto attuale di output
            this.output = this.output + s + "\n";
        }finally{
            this.stringLock.unlock();
        }
    }
    
    // metodo per riversare il contenuto della stringa sulla shell di output
    public void svuotaOutput (){
        this.stringLock.lock();
        
        try{
            System.out.println (this.output);
            this.output = "";
        }finally{
            this.stringLock.unlock();
        }
    }
    
    // metodi funzionali dell'oggetto negozio
    
    // metodo invocato dal barbiere per servire i clienti
    public void serviClienti () throws InterruptedException{
        // acquisisco il semaforo clienti
        this.clienti.acquire();
        
        // se sono qua significa che sono sveglio e quindi é arrivato un cliente
        this.println("Barbirere serve un cliente....");
        
        // devo individuare, tra quelli in attesa, il cliente con i capelli piu
        // lunghi
        Cliente daTagliare = trovaClienteMigliore();
        this.println("Taglio i capelli a "+daTagliare.getName()+
                     " con lunghezza capelli "+daTagliare.getLunghezzaCapelli());
        
        //simulo il tempo necessario per tagliare i capelli
        Thread.sleep(10);
        
        // devo liberare una sedia occupata e risvegliare il cliente
        // cui ho appena tagliato i capelli
        // acquisisco il lock per la mutua esclusione
        this.lock.lock();
        try{
            this.sedieDisponibili++;
            
            // rimuovere il cliente dalla coda
            this.codaClienti.remove(daTagliare);
            
            // sveglio il thread Cliente attraverso il suo metodo
            // pubblico di risveglio
            daTagliare.risveglia();
        }finally{
            this.lock.unlock();
        }
    }//end metodo serviCliente
    
    // metodo utilizzato dai clienti per entrare in negozio
    public boolean entra (Cliente c){
        boolean ret = true;
        
        // controllo se ci sono sedie disponibili
        this.lock.lock();
        
        try{
            if (this.sedieDisponibili > 0){
                // entro in coda ed aspetto il mio turno
                this.println("Cliente "+c.getName() +
                             " si mette in coda con "+ c.getLunghezzaCapelli());
                // decremento il numero di sedie disponibili
                this.sedieDisponibili--;
                
                // inserisco me stesso nella coda dei clienti in attesa
                this.codaClienti.add(c);
                
                // sveglio il barbiere se dorme
                this.clienti.release();
                
                // mi sospendo con il mio sistema di sospensione interno
                c.setCondition(this.lock.newCondition());
                c.sospendi(); // blocca il cliente fino al suo risveglio
                              // ad opera del barbiere
            }//end if
            else{
                //esco senza tagliare i capelli
                ret = false;
                this.println("Il cliente "+
                             c.getName()+
                             " esce senza tagliare i capelli");
            }//end else
        }catch(InterruptedException e){
            System.out.println(e);
        }finally{
            this.lock.unlock();
        }
        
        
        return ret;
    }// end metodo entra
    
    
    // metodo per trovare il cliente con i capelli più lunghi
    private Cliente trovaClienteMigliore (){
        Cliente migliore = null;
        int maxL = -1;
        for (int i = 0;i < this.codaClienti.size();i++){
            Cliente c = this.codaClienti.get(i);
            if(c.getLunghezzaCapelli() > maxL){
                maxL = c.getLunghezzaCapelli();
                migliore = c;
            }
            
        }
        
        return migliore;
    }
}
