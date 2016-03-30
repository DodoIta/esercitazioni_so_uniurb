/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produttoreconsumatore_semaforo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
/* Dovrebbe essere ReentrantLock ma l'IDE (che funziona benissimo) non lo prendeva */

/**
 *
 * @author flavio
 */
public class BufferCircolare {
    /* Oggetto condiviso tra il produttore ed il consumatore */
    /* L'oggetto dev'essere thread-safe */
    
    /* Attributi interni funzionali */
    private int buffer[];
    
    /* Puntatori logici di inserimento e di rimozione */
    private int in,
                out;
    
    /* Attributi di sincronizzazione */
    ReadLock lock; /* Lock per la mutua esclusione (a protezione delle
                                             variabili condivise in ed out) */
    
    Semaphore notFull;  /* Semaforo per sospendere il consumatore quando il
                                                         buffer sara' pieno */
    
    /* TIP: il nome del semaforo di solito e' il nome del segnale che lo svegliera' */
    
    Semaphore notEmpty; /* Semaforo per sospendere il produttore quando il
                                                        buffer sara' vuoto */

    /* Costruttore della classe */
    public BufferCircolare (int n){
        /* Inizializzo gli attributi funzionali */
        this.buffer = new int[n];
        this.in = 0;
        this.out = 0;
        
        /* Inizializzo gli attributi di sincronizzazione */
        this.lock = new ReentrantReadWriteLock().readLock();
        
        /* Inizializzo il numero di permessi alla dimensione del buffer cosi'
           il produttore potra' da subito inserire n elementi consumando i
           permessi */
        this.notFull = new Semaphore (n);
        
        /* Inizializzo a zero il semaforo del consumatore poiche' all'inizio
           non ci sara' nemmeno un elemento da consumare */
        this.notEmpty = new Semaphore (0);
    }
    
    /* Metodo di inserimento elemento nel buffer
       metodo thread-safe e bloccante in caso di buffer pieno
    */
    public void inserisci (int elemento){
        /* Cerco di acquisire un permesso sul semaforo notFull */
        try{
            this.notFull.acquire ();
            /* Se sono qui significa che c'era un permesso e quindi c'e' un
                                                    posto libero nel buffer */
            /* Inizio a scrivere */
            /* INIZIO SEZIONE CRITICA */
            this.lock.lock ();
            this.buffer[this.in] = elemento;
            this.in = (this.in + 1)%this.buffer.length;
            
            /* Notifico ad un eventuale consumatore in attesa che ho appena
                                                 inserito un nuovo elemento */
            this.notEmpty.release();
            System.out.println ("Produttore ha inserito: "+elemento);
        } catch (InterruptedException e){
            System.out.println (e);
        }finally{
            this.lock.unlock ();
            /* FINE SEZIONE CRITICA */
        }
    } /* Fine metodo inserisci. */
    
    /* Metodo per prelevare gli elementi dal buffer */
    /* metodo thread-safe e bloccante in caso di buffer vuoto */
    public int consuma (){
        int valore = 0;
        
        /* Cerco di acquisire il semaforo notEmpty per verificare se ci sono
                                                       elementi da consumare */
        try{
            this.notEmpty.acquire ();
            /* se sono qui significa che ci sono elementi da consumare nel
                                                                    buffer */
            /* consumo un elemento */
            /* INIZIO SEZIONE CRITICA */
            this.lock.lock();
            
            valore = this.buffer [this.out];
            
            /* Aggiorno il puntatore logico out */
            this.out = (this.out + 1) % this.buffer.length;
            
            /* Notifico al produttore che c'e' uno spazio in piu' nel buffer */
            this.notFull.release();
            
            System.out.println("Consumatore ha consumato: " + valore);
        }catch (InterruptedException e){
            System.out.println (e);
        }finally{
            this.lock.unlock ();
            /* FINE SEZIONE CRITICA */
        }
        
        return valore;
    } 
} /* Fine dichiarazione della classe */
