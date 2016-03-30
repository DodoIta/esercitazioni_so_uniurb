/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ziputil;

/**
 *
 * @author Flavio Mascetti
 */
public class ZipUtil {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
      // testo il metodo di compressione
        
      // controllo sui parametri di lancio
      if (args.length != 3)
      {
        System.out.println ("ZipUtil [-d, -c] [Dir] [archivio]");
        System.exit (4);
      }
      
      // controllo il parametro -d o -c
      if (args[0].equals("-c"))
      {
        // comprimi
        ZipCore.comprimiZip (args[1], args[2]);
      }
      else if (args[0].equals("-d"))
      {
        // decomprimi
        System.out.println("Metodo non implementato");
      }
      else
      {
        System.out.println("opzione " + args[0] + " non nota..");
        System.exit(5);
      }
    }
    
}
