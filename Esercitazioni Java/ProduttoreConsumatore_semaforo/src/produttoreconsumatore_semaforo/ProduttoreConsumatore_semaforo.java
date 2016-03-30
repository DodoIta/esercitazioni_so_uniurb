/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package produttoreconsumatore_semaforo;

/**
 *
 * @author flavio mascetti
 */
public class ProduttoreConsumatore_semaforo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        /* Creo un'istanza di bufferCircolare */
        BufferCircolare buffer = new BufferCircolare (25);
        
        /* Creo il produttore */
        Produttore produttore = new Produttore(buffer,
                                               "Produttore");
        
        /* Creo il consumatore */
        Consumatore consumatore = new Consumatore(buffer,
                                                  "Consumatore");
        
        /* Lancio i thread */
        produttore.start();
        consumatore.start();
        
        /* Attendo la terminazione dei thread figli */
        try{
            produttore.join();
            consumatore.join();
        }catch (InterruptedException e){
            System.out.println(e);
        }
        
        System.out.println("Tutti i thread sono terminati...");
    }
    
}
