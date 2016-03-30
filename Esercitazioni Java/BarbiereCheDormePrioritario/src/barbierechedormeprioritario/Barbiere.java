/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbierechedormeprioritario;

/**
 *
 * @author flavio
 */
public class Barbiere extends Thread {
    // attributo per condividere l'oggetto negozio
    private Negozio negozio;
    
    // costruttore
    public Barbiere (Negozio n){
        super("Barbiere");
        this.negozio = n;
    }
    
    // corpo del thread
    @Override
    public void run(){
        boolean isAlive = true;
        this.negozio.println("Barbiere inizia l'esecuzione....");
        while (isAlive){
            try{
                this.negozio.serviClienti();
            }catch (InterruptedException e){
                // riceviamo l'interrupt e terminiamo l'esecuzione
                System.out.println(e);
                isAlive = false;
            }
        }
        this.negozio.println("Barbiere termina l'esecuzione....");
    }
}
