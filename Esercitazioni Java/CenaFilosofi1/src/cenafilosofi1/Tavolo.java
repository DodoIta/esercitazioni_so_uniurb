/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cenafilosofi1;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;
/* Dovrebbe essere ReentrantLock ma l'IDE (che funziona benissimo) non lo prendeva */

/**
 *
 * @author flavio
 */
public class Tavolo {
    // attributi funzionali e di sincronizzazione
    // array di semafori binari (lock) che simula le 5 bacchette disponibili
    private ReadLock bacchette[];
    
    // semaforo contatore per limitare il numero di filosofi che si siedono
    // contemporaneamente al tavolo
    Semaphore ingresso;
    
    // generatore di numeri casuali per creare tempi aleatori
    private Random rnd;
    
    // costruttore del tavolo
    public Tavolo (){
        // inizializzo gli attributi interni
        this.bacchette = new ReadLock[5];
        
        // ciclo per inizializzare tutte le bacchette
        for (int i = 0; i < this.bacchette.length; i++)
            this.bacchette[i] = new ReentrantReadWriteLock().readLock();
        
        // inizializzo il semaforo contatore
        this.ingresso = new Semaphore (4, true);
        // l'argomento true definisce la politica di risveglio sul semaforo
        // di tipo FIFO.
        
        // inizializzo il generatore di numeri casuali
        this.rnd = new Random();
    }
    
    // metodi funzionali dell'oggetto condiviso
    
    // il metodo forwarda l'eccezzione di tipo InterruptedException all'invocante
    public void mangia (Filosofo f) throws InterruptedException{
        // il filosofo acquisisce un permesso sul semaforo contatore di ingresso
        this.ingresso.acquire();
        
        /* il filosofo iesimo cerca di ottenere la bacchetta i e la bacchetta
           (i + 1)%5*/
        int index = f.getIndex();
        
        System.out.println("Il filosofo "+index+" si appresta a mangiare.");
        
        // acquisisco la mia bacchetta destra
        this.bacchette[index].lock();
        
        // acquisisco la mia bacchetta sinistra
        this.bacchette[(index + 1)%5].lock();
        
        // se sono qua significa che posso mangiare
        // ho ottenuto infatti entrambe le bacchette
        System.out.println("Il filosofo "+index+" mangia");
        
        // simulo il tempo necessario per mangiare con una sleep randomica
        Thread.sleep (this.rnd.nextInt(30));
        
        // quando mi sveglio ripongo le bacchette
        this.bacchette[index].unlock();
        this.bacchette[(index+1)%5].unlock();
        
        System.out.println("Il filosofo "+index+" ha deposto le bacchette");
        
        // il filosofo rilascia il permesso sul semaforo di ingresso
        this.ingresso.release();
    }// fine metodo mangia
    
    public void pensa (Filosofo f) throws InterruptedException{
        // simulo il tempo trascorso a pensare attraverso una sleep
        System.out.println ("Il filosofo "+f.getIndex()+" inizia a pensare");
        
        /* penso
        Thread.sleep(this.rnd.nextInt(100));*/
        
        // a questo punto avrò smesso di pensare
        System.out.println("Il filosofo "+f.getIndex()+" smette di pensare");
    }
}
