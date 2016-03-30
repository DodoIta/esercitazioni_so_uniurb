/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiltest;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author Flavio Mascetti
 */
public class UtilTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
      //Creo un'istanza della classe Date per ottenere la data ed ora attuale
      Date now = new Date(); //fa una system call che chiede data e ora
      System.out.println ("Oggi : " + now);
      
      // utilizzo la classe SortedList
      // inizializzo un generatore di numeri pseudo-casuali
      Random rnd = new Random();
      /* ho inizializzato il generatore senza passarvi un seme
         Java lo inizializza con la data in millisecondi
      */
      // estraggo un numero pseudo-casuale nell'intervallo 0-99 e lo passo
      SortedList list = new SortedList();
      
      for (int i=0;
           i < 10;
           i++)
      {
        list.addInteger(rnd.nextInt(100)); // 100% dell'intervallo
      }
      
      //vado a stampare il contenuto della lista prima dell'ordinamento
      System.out.println("\nUNSORTED");
      ArrayList<Integer> array = list.getUnsortedList();
      for(int i=0;
          i < array.size();
          i++)
          System.out.print(array.get(i) + "  ");
      // chiedo la lista ordinata
      System.out.println("\nSORTED");
      array = list.getSortedList();
      for(int i=0;
          i < array.size();
          i++)
          System.out.print(array.get(i) + "  ");
      System.out.println("\n");
    }
    
}
