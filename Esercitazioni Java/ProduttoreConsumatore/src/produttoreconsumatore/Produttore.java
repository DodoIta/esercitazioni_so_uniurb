/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produttoreconsumatore;

/**
 *
 * @author operating
 */
public class Produttore extends Thread{
    // attributo interno per la condivisione 
    // dell'oggetto buffer
    private BufferCondiviso buffer;
    
    // costruttore del Produttore
    public Produttore(String name, BufferCondiviso buffer){
        super(name);
        this.buffer = buffer;
    }
    
    
    // comportamento del thread
    @Override
    public void run(){
        System.out.println("IL thread "+getName()+" inizia a eseguire");
        for(int i = 0; i < 20; i++)
            this.buffer.put(i);
        System.out.println("Il thread "+getName()+" termina..");
    }
    
}











