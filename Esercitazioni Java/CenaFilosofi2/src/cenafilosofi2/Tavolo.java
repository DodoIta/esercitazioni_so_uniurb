/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cenafilosofi2;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

/**
 *
 * @author flavio
 */
// NB. il monitor (implicito) non é utilizzabile neanche all'esame
public class Tavolo { // oggetto condiviso di tipo monitor (esplicito)
    // attributi interni di sincronizzazione
    ReadLock ingresso;
    // garantisce la mutua esclusione tra i metodi dell'oggeto monitor
    
    // variabili condizione per sospendere i 5 filosofi
    Condition self[];
    
    // enumerazione di stati (AFFAMATO,MANGIA,PENSA)
    enum Stati{AFFAMATO, MANGIA, PENSA};
    
    // array per gestire lo stato dei 5 filosofi
    Stati statiFilosofi[];
    
    
    public Tavolo(){
        // inizializzazione degli attributi interni
        this.ingresso = new ReentrantReadWriteLock().readLock();
        
        // alloco l'array di condition e lo inizializzo
        this.self = new Condition[5];
        for (int i = 0; i < this.self.length; i++)
            this.self[i] = this.ingresso.newCondition();
        
        // inizializzo l'array di stati dei filosofi
        this.statiFilosofi = new Stati[5];
        
        // inizializzo tutti i filosofi nello stato AFFAMATO.
        for (int i = 0; i < this.statiFilosofi.length; i++)
            this.statiFilosofi[i] = Stati.AFFAMATO;
    } // end Costruttore
    
    // implementazione dei metodi funzionali del monitor
    // metodo per prelevare le bacchette del filosofo i-esimo
    public void prendiBacchette(int i) throws InterruptedException{
        // il metodo, per fare parte di un monitor deve essere in mutua
        // esclusione con se stesso e con tutti gli altri metodi del monitor
        // come faccio? (un unico lock per tutti i metodi)
        
        // acquisizione del lock per la mutua esclusione
        this.ingresso.lock();
        
        try{
            // INIZIO sezione critica
            
            // il filosofo i-esimo dichiara di essere affamato
            this.statiFilosofi[i] = Stati.AFFAMATO;
            
            // testo il mio vicino destro ed il mio vicino sinistro
            testaVicini(i);
            
            // controllo se il mio stato é MANGIA
            // altrimenti significa che non posso mangiare e devo sospendermi
            // sulla mia variabili condizione
            while (this.statiFilosofi[i] != Stati.MANGIA)
                this.self[i].await();
            
            // FINE sezione critica
        }finally{
            this.ingresso.unlock();
        }
    }// end metodo prendiBacchette
    
    // metodo per liberare le bacchette
    public void lasciaBacchette (int i){ // non essendo un metodo bloccante
                                         // non facciamo la throw di InterrExc
                                         // visto che non può capitare un eccezz
                                         // di questo tipo
        // mutua esclusione con gli altri metodi del monitor e con se stesso
        this.ingresso.lock();
        try{
            // il filosofo i-esimo dichiara di non voler più mangiare
            this.statiFilosofi[i] = Stati.PENSA;
            
            // invoco il test dei vicini per conto del mio vicino destro
            testaVicini((i+1)%5);
            
            // per contro del mio vicino sinistro
            testaVicini((i+4)%5);
        }finally{
            this.ingresso.unlock();
        }
        
    }// end metodo lasciaBacchette
    
    // metodo testa vicini
    // in questo caso non abbiamo bisogno di utilizzare un ReentrantLock
    // visto che lo renderemo privato e quindi invocabile soltanto dai metodi
    // interni della classe che sono entrambi sincronizzati
    private void testaVicini(int i){
        // controllo sullo stato dei vicini destro e sinistro rispetto ad i
        if (this.statiFilosofi[(i+1)%5] != Stati.MANGIA &&
            this.statiFilosofi[(i+4)%5] != Stati.MANGIA &&
            this.statiFilosofi[i] == Stati.AFFAMATO){
            // setto lo stato del filosofo a MANGIA
            this.statiFilosofi[i] = Stati.MANGIA;
            
            // sveglio il filosofo se stava dormendo
            this.self[i].signal();
        }
    }
}// end classe
