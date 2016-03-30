/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoo;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author flavio
 */
public class Capanna {
    private int porzioniDisponibili;
    private ReentrantLock lock;
    private Semaphore porzioniPronte;
    private Semaphore porzioniFinite;
    
    public Capanna(){
        this.porzioniDisponibili = 0;
        this.lock = new ReentrantLock();
        this.porzioniPronte = new Semaphore (0,true);
        this.porzioniFinite = new Semaphore (0);
    }
    
    public void richiediDiMangiare (Leone l) throws InterruptedException{
        System.out.println(l.getName()+" vuole mangiare...");
        try{
            this.lock.lock();
            if (this.porzioniDisponibili == 0){
                System.out.println("Porzioni Finite");
                this.porzioniFinite.release();
            }
            this.lock.unlock();
            this.porzioniPronte.acquire();
            l.sleep(200);
            this.lock.lock();
            this.porzioniDisponibili --;
            System.out.println(l.getName()+" ha mangiato.");
            if (this.porzioniDisponibili == 0){
                System.out.println("Porzioni Finite");
                this.porzioniFinite.release();
            }
            this.lock.unlock();
        }catch (InterruptedException e){
            System.out.println(e);
        }
    }
    
    public void distribuisciPorzioni(int porzioniPreparate) throws InterruptedException{
        System.out.println("Il Guardiano prepara "+porzioniPreparate+" porzioni.");
        this.lock.lock();
            this.porzioniDisponibili += porzioniPreparate;
            this.porzioniPronte.release(porzioniPreparate);
        this.lock.unlock();
        
        this.porzioniFinite.acquire();
        System.out.println("Il guardiano entra nella capanna");
    }
}
