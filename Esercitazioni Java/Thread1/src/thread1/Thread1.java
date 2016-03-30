/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thread1;

/**
 *
 * @author operating
 */
public class Thread1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // allocare nuovi thread
        Esempio1 th1 = new Esempio1("Th1","Flavio Mascetti"),
                 th2 = new Esempio1("Th2","Mex 2"),
                 th3 = new Esempio1("Th3","Mex 3"),
                 th4 = new Esempio1("Th4","Mex 4");
        
        
        // mettiamo il thread main in attesa per la terminazione dei figli
        
        th1.start();
        th2.start();
        th3.start();
        th4.start();
        
        try{
            th1.join();
            th2.join();
            th3.join();
            th4.join();
        }catch (InterruptedException e){
            System.out.println(e);
        }
        
        System.out.println ("Tutti i thread sono terminati");
    }
    
}
