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
public class ProduttoreConsumatore {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // creo l'oggeto da condividere
        BufferCondiviso buffer= new BufferCondiviso(1);
        // creo il produttore ed il consumatore
        Produttore produttore = new Produttore("Produttore", buffer);
        Consumatore consumatore = new Consumatore("Consumatore", buffer);
        
        // lancio i  thread
        produttore.start();
        consumatore.start();
        
        try{
            produttore.join();
            consumatore.join();
        }catch(InterruptedException e){
            System.out.println(e);
        }
        System.out.println("Tutti i thread sono terminati...");
    }
    
}







