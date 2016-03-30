/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parcogiochi;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author flavio
 */
public class MontagneRusse {
    private int postiOccupati;
    private Condition vetturaPiena;
    private Condition vetturaVuota;
    private Condition giroFinito;
    private Semaphore posti;
    private ReentrantLock lock;
    
    public MontagneRusse(){
        this.lock = new ReentrantLock();
        this.posti = new Semaphore(0,true);
        this.postiOccupati = 0;
        this.vetturaPiena = lock.newCondition();
        this.vetturaVuota = lock.newCondition();
        this.giroFinito = lock.newCondition();
    }
    
    public void board(Passeggero p){
        try{
            this.posti.acquire();
            System.out.println(p.getName()+" sale sulla vettura");
            //  INIZIO SEZIONE CRITICA
            // acquisisco il lock
            this.lock.lock();
            this.postiOccupati ++;
            if (this.postiOccupati < 8){
                this.vetturaPiena.await();
            }else{
                this.vetturaPiena.signalAll();
            }
        }
        catch(InterruptedException e){
               System.out.println(e); 
        }
        finally{
            this.lock.unlock();
        }
        //  FINE SEZIONE CRITICA
    }
    public void attendiFineGiro(){
        try{
            this.lock.lock();
            this.giroFinito.await();
            this.lock.unlock();
        }catch (InterruptedException e){
            System.out.println(e);
        }
    }
    
    public void unboard(Passeggero p){
        try{
            //  INIZIO SEZIONE CRITICA
            // acquisisco il lock
            this.lock.lock();
            this.postiOccupati --;
            if (this.postiOccupati == 0)
                this.vetturaVuota.signalAll();
            System.out.println(p.getName()+" scende dalla vettura");
        }finally{
            this.lock.unlock();
        }
        //  FINE SEZIONE CRITICA
    }
    
    public void load() throws InterruptedException{
        System.out.println("La vettura é pronta a far salire nuovi passeggeri.");
        try{
        this.lock.lock();
        this.posti.release(8);
        this.vetturaPiena.await();}
        finally{
        this.lock.unlock();}
    }
    
    public void go(Vettura v){
        try{
        v.sleep(300);
        System.out.println("La vettura ha completato il giro.");}
        catch (InterruptedException e){
            System.out.println(e);
        }
    }
    
    public void unload(){
        try{
            this.lock.lock();
            this.giroFinito.signalAll();
            this.vetturaVuota.await();
        }catch (InterruptedException e)
        {System.out.println(e);
        }finally{
            this.lock.unlock();
        }
    }
}
