/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package esamesushibar;

/**
 *
 * @author flavio
 */
public class EsameSushiBar {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creazione dell'oggetto condiviso
        SushiBar bar = new SushiBar();
        
        // creo i 15 thread
        Cliente clienti[] = new Cliente[15];
        for (int i=0; i<clienti.length;i++)
            clienti[i] = new Cliente(i,bar);
        
        // faccio partire i thread
        for (int i=0; i<clienti.length;i++)
            clienti[i].start();
        
        // attendo per la loro terminazione
        for (int i = 0; i < clienti.length;i++)
            try{
                clienti[i].join();
            }catch(InterruptedException e)
            {
                System.out.println(e);
            }
        System.out.println("Simulazione terminata.....");
    }
    
}
