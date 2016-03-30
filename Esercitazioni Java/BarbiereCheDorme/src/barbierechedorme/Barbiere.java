/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbierechedorme;

public class Barbiere extends Thread {
    // attributo interno per la condivisione dell'oggetto negozio
    private Negozio negozio;
    
    // costruttore di barbiere
    public Barbiere(Negozio negozio){
        super("Barbiere");
        this.negozio = negozio;
    }
    
    // metodo run del Barbiere
    @Override
    public void run(){
        // ciclo potenzialmente infinito per gestire la terminazione deferita
        boolean isAlive = true;
        
        while (isAlive){
            try{
                this.negozio.serviCliente();
            }catch(InterruptedException e){
                System.out.println(e);
                isAlive = false;
                /* 
                   al ricevimento dell'eccezione la variabile che controlla il
                   ciclo viene messa a false per terminare l'esecuzione del
                   thread
                */
            }
        }// end ciclo while
        System.out.println("Il barbiere termina l'esecuzione...");
    }// end metodo run
}// end classe
