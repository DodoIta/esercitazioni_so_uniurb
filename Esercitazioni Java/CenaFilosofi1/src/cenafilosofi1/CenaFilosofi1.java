/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cenafilosofi1;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

/**
 *
 * @author flavio
 */
public class CenaFilosofi1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creare un'istanza dell'oggetti condiviso
        Tavolo tavolo = new Tavolo();
        
        // creo un array di 5 filosofi
        Filosofo filosofi[] = new Filosofo[5];
        
        // inizializzo i filosofi
        for (int i = 0; i < filosofi.length; i++)
            filosofi[i] = new Filosofo(i,tavolo);
        
        // faccio partire i filosofi
        for (int i = 0; i < filosofi.length; i++)
            filosofi[i].start();
        
        // attendo per la terminazione dei thread filosofi
        for (int i = 0; i < filosofi.length; i++)
            try{
                filosofi[i].join();
            }catch(InterruptedException e){
                System.out.println(e);
            }
        
        System.out.println("Simulazione terminata...");
    }
    
}
