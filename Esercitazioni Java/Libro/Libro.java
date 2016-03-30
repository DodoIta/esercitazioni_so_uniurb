/* Dichiarazione della classe Libro */

public class Libro
{
  /* Attributi interni */
  /* per la regola dell'incapsulamento nascondo gli attributi
     mettendoli private */
  private int code;
  private String title; /* se la var é formata da due parole scriviamo
                           "longTitle" invece di "long_title" del c */
  private String author;
  
  /* Metodo Costruttore */
  public Libro (int codice,
                String titolo,
                String autore)
  {
    /* assegno agli attributi interni il valore passatomi dall'esterno */
    this.code = codice;
    this.title = titolo;
    this.author = autore;
  }
  
  /* Metodo di funzionalità */
  public void printInfo()
  {
    System.out.println ("Codice libro: " + this.code);
    System.out.println ("Titolo libro: " + this.title);
    System.out.println ("Autore libro: " + this.author);
  }
}
