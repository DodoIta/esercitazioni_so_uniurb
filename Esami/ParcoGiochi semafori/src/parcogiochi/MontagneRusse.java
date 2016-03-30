/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcogiochi;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author flavio
 */
public class MontagneRusse {
    private int postiOccupati;
    
    private Semaphore boardSem;
    
    private Semaphore unboardSem;
    
    private Semaphore loadSem;
    
    private Semaphore unloadSem;
    
    private ReentrantLock lock;
    
    public MontagneRusse(){
        this.boardSem = new Semaphore(0,true);      // FIFO
        this.unboardSem = new Semaphore(0,true);    // FIFO
        this.loadSem = new Semaphore(0);  // e' per la vettura aka 1 solo thread
        this.unloadSem = new Semaphore(0);// e' per la vettura aka 1 solo thread
        this.lock = new ReentrantLock();
        this.postiOccupati = 0;
    }
    
    public void board(Passeggero p) throws InterruptedException{
        this.boardSem.acquire();
        System.out.println(p.getName()+" sale sulla vettura");
            //  INIZIO SEZIONE CRITICA
        // acquisisco il lock
        this.lock.lock();
        try{
            this.postiOccupati ++;
            if (this.postiOccupati == 8)
                this.loadSem.release();
        }finally{
            this.lock.unlock();
            // FINE SEZIONE CRITICA
        }
    }
    
    public void unboard(Passeggero p) throws InterruptedException{
        this.unboardSem.acquire();
        System.out.println(p.getName()+" scende dalla vettura");
        //  INIZIO SEZIONE CRITICA
        // acquisisco il lock
        this.lock.lock();
        try{
            this.postiOccupati --;
            if (this.postiOccupati == 0)
                this.unloadSem.release();
        }finally{
            this.lock.unlock();
        }
        //  FINE SEZIONE CRITICA
    }
    
    public void load() throws InterruptedException{
        System.out.println("La vettura é pronta a far salire nuovi passeggeri.");
        this.boardSem.release(8);
        this.loadSem.acquire();
    }
    
    public void go(Vettura v) throws InterruptedException{
        v.sleep(300);
        System.out.println("La vettura ha completato il giro.");
    }
    
    public void unload() throws InterruptedException{
        this.unboardSem.release(8);
        this.unloadSem.acquire();
        System.out.println ("Tutti i passeggeri sono scesi dalla vettura");
    }
}
