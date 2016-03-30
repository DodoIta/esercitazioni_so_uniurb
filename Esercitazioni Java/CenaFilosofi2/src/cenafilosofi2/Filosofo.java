/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cenafilosofi2;

import java.util.Random;

/**
 *
 * @author flavio
 */
public class Filosofo extends Thread {
    // attributo interno per la condivisione dell'oggetto tavolo
    private Tavolo tavolo;
    
    // indice del filosofo
    private int index;
    
    // generatore di numeri casuali
    private Random rnd;
    
    // attributo per stimare il tempo medio d'attesa del filosofo per prendere
    // le bacchette
    private double avgWaitingTime;
    
    // cotruttore del filosofo
    public Filosofo(int i, Tavolo t){
        // setto il nome al thread
        super("Filosofo_"+i);
        
        // inizializzo i parametri
        this.tavolo = t;
        this.index = i;
        this.avgWaitingTime = 0;
        
        // genero un numeoro casuale
        this.rnd = new Random();
    }// end contruttore
    
    @Override
    public void run(){
        // il filosofo per 50 volte
            // cerca di prendere le bacchette
            // mangia
            // lascia le bacchette
            // pensa
        for (int i = 0; i < 50; i++){
            // cerco di prendere le bacchette
            try{
                // stimo il tempo leggendo l'orologio di sistema prima e dopo
                // l'invocazione del metodo
                long t0 = System.currentTimeMillis();
                this.tavolo.prendiBacchette(this.index); // metodo bloccante
                long t1 = System.currentTimeMillis();
                // incremento la variabile tempo di attesa
                this.avgWaitingTime += t1-t0;
                
                // se sono qui significa che posso mangiare
                System.out.println("Filosofo "+this.index+" mangia...");
                
                // simulo il tempo necessario per mangiare con una sleep
                Thread.sleep(this.rnd.nextInt(30));
                
                // lascio le bacchette
                this.tavolo.lasciaBacchette(this.index);
                
                // il filosofo ora inizia a pensare
                System.out.println("Il filosofo "+this.index+" pensa...");
                
                // simulo il tempo trascorso a pensare con una sleep
                Thread.sleep(this.rnd.nextInt(50));
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }// end ciclofor
        // divido il tempo totale d'attesa per 50 per ottenere il tempo medio
        this.avgWaitingTime /= 50;
        
        System.out.println("Il filosofo "+this.index+" termina");
    }//end run
    
    // metodo di get per leggere avgWaitingTime
    public double getAvgWaitingTime(){
        return this.avgWaitingTime;
    }
}
