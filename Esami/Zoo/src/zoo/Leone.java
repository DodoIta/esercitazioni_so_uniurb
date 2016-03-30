/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoo;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author flavio
 */
public class Leone extends Thread{
    private int arrivo;
    private Random rnd;
    private Capanna c;
    public Leone (Capanna c, int i){
        super("Leone_"+i);
        this.c = c;
        this.rnd = new Random();
        this.arrivo = rnd.nextInt(300 + 1);
    }
    
    @Override
    public void run(){
        
        for (int i = 1; i <= 10; i++){
            try {
                Thread.sleep(this.arrivo);
                c.richiediDiMangiare(this);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        System.out.println(this.getName()+" termina.");
    }
}
