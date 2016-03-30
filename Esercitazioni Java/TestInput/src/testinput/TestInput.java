/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testinput;

import java.io.BufferedReader;   /* oppure java.io.* per tutto l'I/O
                                    (cosa cambia? aumenta il peso) */
import java.io.IOException;
import java.io.InputStreamReader;
/**
 *
 * @author Flavio Mascetti
 */
public class TestInput {

    /**
     * @param args the command line arguments
     */
    public static void main (String[] args) {
        // dichiarazione della variabile che conterrà il wrapper
        BufferedReader reader = null;
        String temp = null;
        
        // inizio operazioni potenzialmente bloccanti/pericolose
        try
        {
          System.out.println ("Inserisci il testo...");
          //creo il wrapper sullo standard input
          reader = new BufferedReader (new InputStreamReader (System.in));
          
          // leggo una riga dallo standard input
          temp = reader.readLine ();
          
          //mi aspetto un'intero e quindi faccio una conversione della stringa in intero
          int valore = Integer.parseInt (temp);
          System.out.println ("Ho letto <" + temp + "> valore intero " + valore);
        }
        catch (IOException e)
        {
          System.out.println (e);
        }
        catch (NumberFormatException n)
        {
          System.out.println ("Errore di conversione in intero per: " + temp);
        }
    }
    
}
