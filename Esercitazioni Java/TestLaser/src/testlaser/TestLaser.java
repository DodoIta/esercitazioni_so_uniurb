/******************************************/
/* Laboratorio 02 - 12 - 2014 Esercizio 1 */
/******************************************/

package testlaser;

/**
 *
 * @author Flavio Mascetti
 */
public class TestLaser {

    /**
     * @param args the command line arguments
     */
    public static void main (String[] args)
    {
      /* Creo due oggetti laser */
      Laser uno;
      Laser due;
      
      /* Creo un oggetto laser ricaricabile */
      LaserRicaricabile r1;
      
      uno = new Laser (100);
      due = new Laser (80);
      r1 = new LaserRicaricabile (40, 100);
      
      /* utilizzo i laser */
      uno.rotate (25);
      due.rotate (45);
      
      uno.fire ();
      due.fire ();
      r1.fire ();
      
      /* scarico tutti i laser sul nemico */
      while (uno.fire ());
      while (due.fire ());
      while (r1.fire ());
      
      /* ricarico il laser */
      r1.charge(40);
      while (r1.fire ());
      
      System.out.println ("Simulazione terminata!!");
      
    }
    
}
