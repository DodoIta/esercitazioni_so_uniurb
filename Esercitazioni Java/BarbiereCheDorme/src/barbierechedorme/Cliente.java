/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbierechedorme;

import java.util.Random;

/**
 *
 * @author flavio
 */
public class Cliente extends Thread {
    // attributo per la condivisione dell'oggetto negozio
    private Negozio negozio;
    
    // generatore di numeri casuali
    private Random rnd;
    
    // indice del cliente
    private int index;
    
    // costruttore del cliente
    public Cliente (Negozio n,
                    int i){
        super("Cliente_"+i);
        this.index = i;
        this.negozio = n;
        this.rnd = new Random();
    }
    
    // metodo di get per la variabile index
    public int getIndex(){
        return this.index;
    }
    
    // metodo run
    @Override
    public void run(){
        // chiede di tagliare i capelli un certo numero di volte
        for (int i = 0; i < 10; i++){
            if (this.negozio.entraNegozio(this))
            {
                // ho tagliato i capelli
                System.out.println("Cliente "+
                                   this.index+
                                   " ha tagliato i capelli");
                
                // simulo la ricrescita
                try{
                    Thread.sleep(200);
                }catch(InterruptedException e){
                    System.out.println(e);
                }
            }
            else
            {
                // il negozio era pieno e non ho potuto tagliare i capelli
                System.out.println("Il Cliente "+
                                   this.index+
                                   " va a casa e riprova in seguito");
                try{
                    Thread.sleep(10);
                }catch (InterruptedException e){
                    System.out.println(e);
                }
            }
        }
        System.out.println("Il Cliente "+ this.index+" termina l'esecuzione.");
    }// end metodo run
}
