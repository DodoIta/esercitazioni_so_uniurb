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

// thread delegato a stampare periodicamente la stringa di output della shell
public class Stampatore extends Thread {
    // attributo per la condivisione del negozio
    private Negozio negozio;
    
    public Stampatore (Negozio n){
        super("Stampatore");
        this.negozio = n;
    }
    
    // metodo run
    @Override
    public void run(){
        // attuo il meccanismo di terminazione deferita
        boolean isAlive = true;
        while (isAlive){
            try{
                Thread.sleep (500);
                this.negozio.svuotaOutput();
            }catch (InterruptedException e){
                System.out.println(e);
                isAlive = false;
            }
        }
        System.out.println ("Thread "+getName()+" terminato....");
    }
    
}
