/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produttoreconsumatore;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author operating
 */
// sarà un buffer circolare di dimensione N
// condiviso tra i thread Consumatore e Produttore
// dovrà essere thread-safe quindi pubblicherà due 
// metodi pubblici per l'accesso al buffer
// put () per inserire un nuovo elemento 
// get () per rimuovere un elemento.
// i metodi put e get saranno bloccanti sospendendo i thread 
// in caso rispettivamente di buffer pieno e buffer vuoto
public class BufferCondiviso {
    // attributi interni dell'oggetto
    // FUNZIONALI
    private int buffer[];
    // puntatori logici di inserimento e rimozione
    private int in,out;
    // numero totale di elementi nel buffer
    private int elementsInBuffer;
    
    // SINCRONIZZAZIONE
    // semaforo binario per la mutua esclusione (Lock)
    ReentrantLock lock;
    // Variabili condition per sospendere il produttore
    // ed il consumatore
    Condition notFull; // sospendere il produttore
    Condition notEmpty;// sospendere il consumatore
    
    // Costruttore dell'oggetto
    public BufferCondiviso(int n){
        // inizializzo gli attributi funzionali
        this.buffer = new int[n];
        this.in = 0;
        this.out = 0;
        this.elementsInBuffer = 0;
        // inizializzo gli attributi di sincronizzazione
        this.lock = new ReentrantLock();
        // le variabili Condition non possono essere inizializzate
        // con l'operatore new ma devono essere create a partire 
        // da un'istanza esistente di ReentrantLock
        this.notEmpty = this.lock.newCondition();
        this.notFull  = this.lock.newCondition();
        
    }
    
    //metodo per l'inserimento di un nuovo elemento nel buffer
    public void put(int element){
        // inizio sezione critica 
        this.lock.lock();
        try{
            // controllo che il buffer non sia pieno
            // altrimenti mi sospendo sulla variabile Condition notFull
            while(this.elementsInBuffer == this.buffer.length){
                this.notFull.await();
                // sospende il thread invocante e rilascia 
                // automaticamente il lock associato alla Comdition
                // quando il thread verrà risvegliato automaticamente 
                // entrerà in competizione per acquisire nuovamente il 
                // lock della sezione critica 
            }
            System.out.println("Inserimento nuovo elemento...");
            this.elementsInBuffer++;
            this.in = (this.in + 1)%this.buffer.length;
            // inserisco l'elemento 
            this.buffer[this.in] = element;
            System.out.println("Numero elementi in buffer: "
                    +this.elementsInBuffer);
            // segnalo ad un eventuale consumatore in attesa la presenza di 
            // un elemento da rimuovere 
            this.notEmpty.signal();
            
        }catch(InterruptedException e){
            System.out.println(e);
        }
        finally{
            this.lock.unlock();
        }
    }// end del metodo put()
    
    
    // metodo per l'estrazione di un elemento dal buffer
    // per opera del consumatore
    public int get(){
        int value = 0;
        // inizio seione critica pre controllare se il buffer è vuoto
        this.lock.lock();
        try{ // SEZIONE CRITICA
            // controllo che il buffer non sia vuoto altrimenti 
            // mi sospendo sulla variabile condition notEmpty
            while(this.elementsInBuffer == 0){
                this.notEmpty.await(); // il lock viene rilasciato automaticamente
            }
            System.out.println("Rimuovo un elemento dal buffer ...");
            // prelevo l'elemento in posizione out
            value = this.buffer[this.out];
            // aggiorno il puntatore logico out 
            this.out = (this.out + 1)%this.buffer.length;
            this.elementsInBuffer--;
            System.out.println("Elementi presenti nel buffer: "
                    +this.elementsInBuffer);
            // segnalo ad un eventuale produttore in attesa che si è appena
            // liberato un posto su cui scirvere
            this.notFull.signal();
            
        }catch(InterruptedException e){
            System.out.println(e);
        }finally{
            this.lock.unlock();
        }
        
        return value;
    }
        
}

































