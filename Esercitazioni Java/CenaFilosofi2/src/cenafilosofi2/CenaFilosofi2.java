/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cenafilosofi2;

/**
 *
 * @author flavio
 */
public class CenaFilosofi2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creo l'instanza dell'oggetto condiviso tavolo
        Tavolo tavolo = new Tavolo();
        
        // creo l'array di filosofi
        Filosofo filosofi[] = new Filosofo[5];
        
        // creo i filosofi
        for (int i = 0; i < filosofi.length; i++){
            filosofi[i] = new Filosofo(i,tavolo);
        }
        
        // lancio i filosofi
        for (int i = 0; i < filosofi.length; i++){
            filosofi[i].start();
        }
        
        // attendo la terminazione dei filosofi
        for (int i = 0; i < filosofi.length; i++)
            try{
                filosofi[i].join();
            }catch(InterruptedException e){
                System.out.println(e);
            }
        
        // i filosofi sono terminati
        System.out.println("Simulazione terminata...");
        
        for (int i = 0; i < filosofi.length; i++){
            System.out.println("Il filosofo "+filosofi[i].getName()+
                               " ha atteso "+filosofi[i].getAvgWaitingTime());
                    
        }
    }
    
}
