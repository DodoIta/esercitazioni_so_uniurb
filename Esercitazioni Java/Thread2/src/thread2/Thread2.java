/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread2;

/**
 *
 * @author operating
 */
public class Thread2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Thread th1 = new Thread (new Esempio2("Th1",100,0.7)),
               th2 = new Thread (new Esempio2("Th2",200,0.5)),
               th3 = new Thread (new Esempio2("Th3",150,0.1)),
               th4 = new Thread (new Esempio2("Th4",200,0.35));
        
        // lancio i thread
        th1.start();
        th2.start();
        th3.start();
        th4.start();
        
        // attendo per la terminazione dei thread figli
        try{
            th1.join();
            th2.join();
            th3.join();
            th4.join();
        }catch(InterruptedException e)
        {
            System.out.println(e);
        }
        System.out.println("Tutti i thread sono terminati...");
    }
    
}
