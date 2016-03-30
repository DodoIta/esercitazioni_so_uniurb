/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbierechedorme;

/**
 *
 * @author flavio
 */
public class BarbiereCheDorme {

    public static void main(String[] args) {
        // creo un'istanza di negozio
        Negozio negozio = new Negozio(5);
        
        // creo il barbiere
        Barbiere barbiere = new Barbiere(negozio);
        
        // creo un array du thread di tipo cliente
        Cliente clienti[] = new Cliente[20];
        
        // inizializzo i thread
        for (int i = 0;
             i < clienti.length;
             i++)
        {
            clienti[i] = new Cliente (negozio,i);
        }
        
        // lancio i thread
        barbiere.start();
        for (int i = 0;
             i < clienti.length;
             i++)
        {
            clienti[i].start();
        }
        
        // mi metto in attesa per la terminazione dei soli clienti
        for (int i = 0;
             i < clienti.length;
             i++)
        {
            try{
                clienti[i].join();
            }catch(InterruptedException e){
                System.out.println(e);
            }
        }
        
        /* ora so che tutti i clienti sono terminati e posso mandare un
           interrupt al barbiere per forzarlo a terminare */
        barbiere.interrupt(); /* 
                                se il thread sta eseguendo l'interrupt viene
                                perso. Se invece era in sleep lo intercetta.
                              */
        try{
            barbiere.join();
        }catch (InterruptedException e)
        {
            System.out.println(e);
        }
        
        System.out.println("Simulazione terminata....");
    }// end main
}// end classe
