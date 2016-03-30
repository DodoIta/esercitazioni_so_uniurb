/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcogiochi;

import java.util.Random;

/**
 *
 * @author flavio
 */
public class Passeggero extends Thread {
    private Random rnd;
    private MontagneRusse m;
    
    public Passeggero (MontagneRusse m, int i){
        super("Passeggero_"+i);
        this.m = m;
        this.rnd = new Random();
    }
    
    @Override
    public void run(){
        try{
            // INIZIO COMPORTAMENTO THREAD
            Thread.sleep(this.rnd.nextInt(1000)+1);
            this.m.board(this);
            
            this.m.unboard(this);
            // FINE COMPORTAMENTO THREAD
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }
}
