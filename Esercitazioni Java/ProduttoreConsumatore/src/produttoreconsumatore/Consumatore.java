/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produttoreconsumatore;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author operating
 */
public class Consumatore extends Thread{
    // attributo interno per la condivisione 
    // dell'oggetto buffer
    private BufferCondiviso buffer;
    
    // costruttore del Produttore
    public Consumatore(String name, BufferCondiviso buffer){
        super(name);
        this.buffer = buffer;
    }
    
    
    // comportamento del thread
    @Override
    public void run(){
        System.out.println("IL thread "+getName()+" inizia a eseguire");
        for(int i = 0; i < 20; i++){
            try {
                System.out.println("Consumato: "+this.buffer.get());
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        System.out.println("Il thread "+getName()+" termina..");
    }
    
}




