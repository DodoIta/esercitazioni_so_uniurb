/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread2;

import java.util.Random;

/**
 *
 * @author operating
 */
public class Esempio2 implements Runnable {
    // uso il metodo 2 dove implemento l'interfaccia
    // Runneable
    
    // attributo interno
    private String name;
    private int sleepTime;
    private double sleepProb;
    
    // generatore di numeri casuali
    private Random rnd;
    
    
    // costruttore
    public Esempio2 (String threadName,
                     int sleepTime,
                     double sleepProb){
        this.name = threadName;
        this.sleepTime = sleepTime;
        this.sleepProb = sleepProb;
        this.rnd = new Random();
    }
    
    @Override
    public void run(){
        // comportamento del thread
        // con una probabilità pari a sleepProb il thread andrà a dormire per
        // un tempo sleepTime
        // estraggo un numero casuale compreso tra 0 ed 1
        double estratto = this.rnd.nextDouble();
        
        // confronto l'estratto con la probabilità di sleep
        if (estratto <= this.sleepProb){
            // il thread va a dormire per sleepTime
            System.out.println("Il thread "+this.name+" va a dormire per "+this.sleepTime);
            try{Thread.sleep(this.sleepTime);}
            catch(InterruptedException e){
                System.out.println(e);
            }
        }else
        {
            // faccio altro...
            System.out.println("Il thread "+
                               this.name+
                               " esegue senza andare a dormire");
            for (int i = 0; i < 10; i++)
            {
                System.out.print(".");
            }
            System.out.println();
        }
        System.out.println("Il thread "+
                           this.name+
                           " termina esecuzione....");
    }
    
}
