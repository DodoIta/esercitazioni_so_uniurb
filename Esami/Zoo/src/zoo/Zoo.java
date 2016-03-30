/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zoo;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author flavio
 */
public class Zoo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Capanna c = new Capanna();
        Leone l[] = new Leone[20];
        Guardiano g = new Guardiano(c);
        
        // creo i leoni
        for (int i = 0; i < l.length; i++){
            l[i] = new Leone(c,i);
        }
        
        // avvio il guardiano
        g.start();
        
        // avvio i leoni
        for (int i = 0; i < l.length; i++)
            l[i].start();
        
        // attendo la terminazione dei leoni
        for (int i = 0; i < l.length; i++){
            try {
                l[i].join();
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
        
        g.interrupt();
        try{
            g.join();
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }
    
}
