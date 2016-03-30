/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package copy;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 *
 * @author Flavio Mascetti
 */
public class Copy {

    /**
     * Clone del comando cp di linux (copia di un file byte a byte)
     */
    public static void main(String[] args)
    {
      // Faccio la copia del file sorgente byte a byte nel file destinazione
      
      // Controllo che il numero di argomenti di lancio sia esattamente 2
      if (args.length != 2)
      {
        System.out.println ("Copy [src] [dest]");
        System.exit (1);
      }
      
      // apro uno stream in input
      // apro uno stream in output
      // leggo un byte da input e lo scrivo in output
      
      
      try
      {
        // é buona norma fare un controllo sui file
        // stream in input
        FileInputStream fIn = new FileInputStream (args[0]);
        
        // stream in output
        FileOutputStream fOut = new FileOutputStream (args[1]);
        
        int bTemp;
        // copio byte per byte (-1 significa endoffile)
        while ((bTemp = fIn.read())!= -1)
        {
          fOut.write(bTemp);
        }
        
        // il file di input é terminato
        
        // chiudo gli stream di input e output
        fIn.close ();
        fOut.close ();
        System.out.println ("Copia terminata...");
      }
      catch (IOException e)
      {
        System.out.println (e);
      }
    }
    
}
