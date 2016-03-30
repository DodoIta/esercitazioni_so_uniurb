/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcogiochi;

/**
 *
 * @author flavio
 */
public class ParcoGiochi {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        MontagneRusse m = new MontagneRusse();
        Vettura v = new Vettura(m);
        
        // creo 32 thread passeggero
        Passeggero p[] = new Passeggero[32];
        
        // inizializzo i passeggeri
        for (int i = 0; i < p.length;i++){
            p[i] = new Passeggero(m,i);
        }
        
        // avvio i passeggeri
        for (int i = 0; i < p.length;i++){
            p[i].start();
        }
        
        // avvio la vettura
        v.start();
        
        // attendo per la loro terminazione
        for (int i = 0; i < p.length;i++)
            try{
                p[i].join();
            }catch(InterruptedException e)
            {
                System.out.println(e);
            }
        
        // terminati i passeggeri termino anche la vettura
        v.interrupt();
        
        try{
            v.join();
        }catch (InterruptedException e)
        {
            System.out.println(e);
        }
        
        System.out.println("Simulazione terminata.....");
        
    }
    
}
