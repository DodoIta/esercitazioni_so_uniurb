package testlaser;

/**
 *
 * @author Flavio Mascetti
 */

/* Questa classe eredita tutte le caratteristiche della classe laser
   (compresi attributi e funzionalita')
*/
public class LaserRicaricabile extends Laser
{
  /* terzo attributo oltre a quelli ereditati dal padre */
  int range;
  
  /* Dichiaro il costruttore del padre */
  public LaserRicaricabile (int energy, int startRange)
  {
    /* Invoco il costruttore di mio padre attraverso l'operatore super */
    super (energy);
    this.range = startRange;
    super.angle = 50; //inizializzo di default l'angolo a 50 gradi
  }
  
  /* nuova funzionalita' per caricare l'energia */
  public void charge (int newEnergy)
  {
    System.out.println ("LASER: ricarico energia di " + newEnergy + " unita'");
    super.energy += newEnergy;
  }
}
