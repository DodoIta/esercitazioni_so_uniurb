/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoo;

import java.util.Random;

/**
 *
 * @author flavio
 */
public class Guardiano extends Thread {
    private Boolean isAlive;
    private Capanna c;
    private Random rnd;
    public Guardiano (Capanna c){
        super("Guardiano");
        this.c = c;
        this.rnd = new Random();
        this.isAlive = true;
    }
    
    @Override
    public void run(){
        while (isAlive){
            try{
                this.c.distribuisciPorzioni(rnd.nextInt(5) + 2);
            }catch (InterruptedException e){
                System.out.println(e);
                this.isAlive = false;
            }
        }
    }
}
