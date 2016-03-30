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
public class Esempio1 extends Thread {
    private String toPrint;
    
    //metodo costruttore
    public Esempio1 (String threadName, String daStampare){
        // passo la stringa threadName al costruttore
        // di mio padre per settarla come nome del thread
        super (threadName); // super deve essere eseguita sempre per prima
        
        // inizializzo l'attributo interno
        this.toPrint = daStampare;
    }
    
    // metodo run che identifica il comportamento del thread
    @Override  // ci fa capire che stiamo sovrascrivendo un metodo che esiste
    public void run () {
        // da qui inizia il comportamento del thread
        // devo stampare la mia stringa carattere per carattere
        for (int i = 0; i < this.toPrint.length(); i ++){
            System.out.print (this.toPrint.charAt(i));
        }
        System.out.println("\n il thread "+this.getName()+" termina");
        
        // qui finisce il comportamento del thread
        // il thread muore e viene disallocato dalla VM
    }
    
}
