/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produttoreconsumatore_semaforo;

import java.util.Random;

/**
 *
 * @author flavio
 */
public class Consumatore extends Thread {
    /* Thread produttore che produce elemetni con una certa aleatorieta' di
                                                                performance */
    
    /* Attributi interni per la condivisione del buffer circolare */
    private BufferCircolare buffer;
    
    /* Generatore di numeri casuali per creare performance aleatorie */
    private Random rnd;
    
    /* Costruttore della classe */
    public Consumatore (BufferCircolare buff,
                        String nome){
        /* Invoco il costruttore del padre per settare il nome */
        super(nome);
        
        /* Inizializzo il riferimento interno al buffer condiviso */
        this.buffer = buff;
        
        /* Inizializzo il generatore di numeri casuali */
        rnd = new Random ();
    }
    
    /* Comportamento del thread */
    public void run(){
        for (int i = 0;
             i < 50;
             i++){
            /* Consuma un elemento */
            int elemento = this.buffer.consuma();
            
            /* Attendo un tempo casuale compreso tra 0 e 249 millisecondi */
            try{
                Thread.sleep(this.rnd.nextInt(250));
                
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }
        
        System.out.println("Il Consumatore termina l'esecuzione");
    }
}
