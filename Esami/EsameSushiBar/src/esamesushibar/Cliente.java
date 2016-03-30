/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esamesushibar;

import java.util.Random;

/**
 *
 * @author flavio
 */
public class Cliente extends Thread {
    // attributo interno per la condivisione del sushibar
    private SushiBar bar;
    
    // generatore di numeri casuali
    private Random rnd;
    
    // indice del thread
    private int index;
    
    // metodo costruttore
    public Cliente (int i,
                    SushiBar b){
        super ("Cliente_"+i);
        this.index = i;
        this.bar = b;
        this.rnd = new Random();
    }
    
    @Override
    public void run(){
        // simulo l'arrivo casuale al sushibar sospendendo il thread per un tempo casuale prima di invocare il metodo entra
        try{
            Thread.sleep(this.rnd.nextInt(1000)+1);
            // invochiamo il metodo per entrare nel sushibar
            this.bar.entra(this);
            // se sono qui significa che il metodo entra é ritornato e posso iniziare a mangiare
            System.out.println("Il cliente "+this.index+" mangia...");
            // simulo il tempo necessario a mangiare
            Thread.sleep(300);
            // ora posso invocare il metodo esci per notificare agli altri thread la possibilità di entrare nel sushibar
            this.bar.esci(this);
        }
        catch(InterruptedException e){
            System.out.println(e);
        }
        System.out.println("Il cliente "+this.index+" termina l'esecuzione.");
    }//end metodo run
    
    public int getIndex(){
        return this.index;
    }
}
