/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cenafilosofi1;

/**
 *
 * @author flavio
 */
public class Filosofo extends Thread {
    
    // attributo per la condivisione dell'oggetto tavolo
    private Tavolo tavolo;
        
    // attributo che identifica l'indice del filosofo
    private int index;
    
    public Filosofo (int i, Tavolo t){
        super ("Filosofo_"+i);
        this.tavolo = t;
        this.index = i;
    }
    
    // metodo di get dell'attributo privato index
    public int getIndex(){
        return this.index;
    }
    
    // metodo run del thread
    @Override
    public void run(){
        for (int i = 0; i < 50; i++){
            try{
                this.tavolo.mangia(this);
                this.tavolo.pensa(this);
            }
            // catchamo le eccezzioni forwardate da mangia e pensa
            catch(InterruptedException e){
                System.out.println(e);
            }
        }
        
        System.out.println ("Il thread filosofo "+this.index+" termina.....");
    }
}
