/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbierechedormeprioritario;

import java.util.Random;
import java.util.concurrent.locks.Condition;

/**
 *
 * @author flavio
 */
public class Cliente extends Thread {
    // attributi interni
    private Negozio negozio;
    private int index;
    private Random rnd;
    
    // attributo per calcolo del tempo medio di attesa
    private double tempoMedioAttesa;
    private int tagli;
    
    private int lunghezzaCapelli;
    
    // attributo per attuare il meccanismo di sospensione privato
    Condition myCondition;
    
    // contruttore di cliente
    public Cliente(Negozio n,
                   int index){
        super("Cliente_ "+index);
        this.index = index;
        this.negozio = n;
        this.tempoMedioAttesa = 0;
        this.tagli = 0;
        
        // inizializzo gli attributi funzionali
        this.rnd = new Random ();
        this.lunghezzaCapelli = this.rnd.nextInt(100);
    }
    
    // metodi di utilities per sospendere e risveglire il cliente
    
    // vado a settare la mia condition interna con l'istanza che viene passata
    // dall'esterno
    public void setCondition (Condition c){
        this.myCondition = c;
    }
    
    // sospendo il thread invocando await sulla mia condition. Inoltro
    // l'eccezione al metodo invocante
    public void sospendi() throws InterruptedException{
        myCondition.await();
    }
    
    // metodo per risvegliare il thread
    public void risveglia(){
        this.myCondition.signal();
    }
    
    // metodo di get per la variabile interna lunghezza capelli
    public int getLunghezzaCapelli(){
        return this.lunghezzaCapelli;
    }
    
    // metodo di get per la variabile interna index
    public int getIndex(){
        return this.index;
    }
    
    // corpo del thread
    @Override
    public void run(){
        // ripetiamo 10 volte lo stesso comportamento
        for (int i = 0; i < 50; i++){
            long t0 = System.currentTimeMillis();
            boolean tagliato = this.negozio.entra(this);
            long t1 = System.currentTimeMillis();
            if (tagliato){
                this.negozio.println("Cliente "+this.getName()+" ha taglito i capelli");
                
                // aggiorno la somma del tempo d'attesa
                this.tempoMedioAttesa += (t1 - t0);
                this.tagli++;
                
                // simuliamo la ricrescita
                try{
                    Thread.sleep(50);
                }catch (InterruptedException e){
                    System.out.println(e);
                }
            } // end if
            else
            {
                this.negozio.println("Cliente "+this.getName()+" non ha tagliato i capelli");
                try{
                    Thread.sleep(10);
                }catch(InterruptedException e){
                    System.out.println(e);
                }
            } // end else
        } // end for
        
        // calcolo la media dei tempi d'attesa
        if (this.tagli > 0)
            this.tempoMedioAttesa /= this.tagli;
    } // end run
    
    // metodo per leggere il tempo di attesa medio
    public double getTempoMedioAttesa (){
        return this.tempoMedioAttesa;
    }
}