public class Main
{
  public static void main (String args[])
  {
    /* Creo istanza di libro */
    Libro primoLibro = new Libro (1, "Sistemi Operativi", "Emanuele Lattanzi");
    Libro secondoLibro = new Libro (2, "Reti di Calcolatori", "Della Selva");
    
    /* Testo la funzionalità dei libri */
    primoLibro.printInfo();
    System.out.println();
    secondoLibro.printInfo();
  }
}
