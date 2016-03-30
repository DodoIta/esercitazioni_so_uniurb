package testlaser;

/**
 *
 * @author Flavio Mascetti
 */
public class Laser
{
  /* Attributi interni della classe */
  int energy;
  int angle;
  
  /* Dichiaro una costante per il consumo energetico ad ogni sparo */
  
  static final int ENERGY_PER_FIRE = 10;
  
  /* metodo costruttore */
  public Laser (int startEnergy)
  {
    this.energy = startEnergy;
    this.angle = 0;
  }
  
  /* funzionalita' del laser */
  
  /* metodo per fare fuoco contro il nemico */
  public boolean fire ()
  {
    /* Controllo che ci sia abbastanza energia per fare fuoco, se si decremento
       il valore dell'energia della costante
    */
    boolean result = true;
    if (this.energy >= ENERGY_PER_FIRE)
    {
      /* Posso sparare */
      System.out.println ("LASER: fuoco!!");
      this.energy -= ENERGY_PER_FIRE;
    }
    else
    {
      /* Non posso sparare */
      System.out.println ("LASER: energia terminata.");
      result = false;
    }
    return result;
  }
  
  /* metodo per modificare l'orientamento del laser */
  public void rotate (int newAngle)
  {
    System.out.println ("LASER: ruotato a " + newAngle + "gradi");
    this.angle = newAngle;
  }
}
/* NB. Tutto quello dichiarato static verrà allocato all'inizio dell'esecuzione
   quindi, non c'é bisogno che l'oggetto venga creato
*/