/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utiltest;

import java.util.ArrayList;

/**
 *
 * @author Flavio Mascetti
 */

/* Questa classe contiene una lista di numeri interi ed ha la capacità di
   ritornare la lista ordinata. Si basa sulla classe ArrayList.
*/
public class SortedList
{
  // Attributi interni alla classe
  // Lista non ordinata di elementi
  private ArrayList<Integer> unsortedList;
  // Lista ordinata
  private ArrayList<Integer> sortedList;
  
  // Costruttore della classe
  public SortedList()
  {
    // inizializzo gli attributi interni
    this.unsortedList = new ArrayList<Integer>();
    this.sortedList = new ArrayList<Integer>();
  }
  
  // funzionalità di inserimento elemento
  public void addInteger (int value)
  {
    this.unsortedList.add(value);
  }
  
  // metodi di get per gli attributi interni
  
  // metodo per ottenere la lista non ordinata
  public ArrayList<Integer> getUnsortedList ()
  {
    return this.unsortedList;
  }
  
  // metodo per ottenere la lista ordinata
  // ordina la lista partendo da quella non ordinata
  public ArrayList<Integer> getSortedList ()
  {
    // devo ordinare la lista non ordinata e copiarla in sortedList
    int size = this.unsortedList.size(); // numero di elementi della lista
    /* estraggo un elemento alla volta dalla lista unsorted
       e lo copio nella lista sorted
    */
    for (int i = 0;
         i < size;
         i++)
    {
      // devo estrarre il valore minimo dalla lista unsorted
      int minPos = findMin (this.unsortedList);
      this.sortedList.add(this.unsortedList.get(minPos));
      
      // rimuovo il valore dalla lista unsorted
      this.unsortedList.remove(minPos);
    }
    /* alla fine del ciclo ho la lista ordinata dal più piccolo al più grande
       in sortedList, la unsortedList sarà vuota.
    */
    return this.sortedList;
  }
  
  // implementazione del motodo findMin
  private int findMin(ArrayList<Integer> array)
  {
    int minPos = 0;
    int min = array.get(0);
    for (int i = 1;
         i < array.size();
         i++)
    {
      int tmp = array.get(i);
      if (tmp < min)
      {
        min = tmp;
        minPos = i;
      }
    }
    return minPos;
  }
}
