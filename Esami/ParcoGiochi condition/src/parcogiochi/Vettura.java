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
public class Vettura extends Thread {
    private boolean isAlive;
    private MontagneRusse m;
    
    public Vettura (MontagneRusse m){
        super("Vettura");
        this.isAlive = true;
        this.m = m;
    }
    
    @Override
    public void run(){
        while (this.isAlive){
            try{
                /* Inizio comportamento del thread */
                    // Faccio salire i passeggeri
                    this.m.load();
                
                    // Faccio partire la vettura
                    this.m.go(this);
                
                    // Faccio scendere i passeggeri
                    this.m.unload();
                    
                /* Fine comportamento del thread */
            }catch(InterruptedException e){
                System.out.println(e);
                this.isAlive = false;
                /* 
                   al ricevimento dell'eccezione la variabile che controlla il
                   ciclo viene messa a false per terminare l'esecuzione del
                   thread
                */
            }
        }// end ciclo while
        System.out.println("La vettura termina l'esecuzione...");
    }
}
