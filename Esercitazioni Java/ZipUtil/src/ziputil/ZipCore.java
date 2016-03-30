/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ziputil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

/**
 *
 * @author operating
 */
public class ZipCore
{
  /* 
    avra' i metodi per effettuare la compressione di una directory ed il metodo
    per decomprimere un archivio esistente
  */
  
  // metodo per la compressione di una directory
  public static void comprimiZip (String dirSrc, // percorso della directory
                                  String arch)   // percordo file destinazione
  {
    // controllo i parametri di invocazione
    File sDir = new File (dirSrc);
    File fArc = new File (arch);
    
    // controllo se la directory sorgente esiste
    if (!sDir.exists ())
    {
      System.out.println ("Directory inesistente");
      System.exit(1);
    }
    
    // controllo se la sorgente e' una directory
    if (!sDir.isDirectory ())
    {
      System.out.println ("Il percorso sorgente non e' una directory");
      System.exit (2);
    }
    
    
    // inizio a creare un archivio zip
    ZipOutputStream zOut = null;
    
    // otteniamo la lista dei file presenti nella dir sorgente
    // per ogni file
        // creo una zipEntry nello stream zip
        // scrivo tutti i byte del file sulla zipEntry
        // in questo caso salteremo tutte le sottocartelle
    
    try
    {
      // chiedo la lista dei file presenti nella dir sorgente
      File files[] = sDir.listFiles ();  // listFiles ritorna un array di file
      
      // inizializzo lo stream di output
      zOut = new ZipOutputStream (new FileOutputStream (arch));
      
      // ciclo su tutti i file presenti nella dir sorgente
      for (int i = 0;
           i < files.length;
           i++)
      {
        // controllo che non sia una directory
        if (!files[i].isDirectory())
        {
          /* 
            effettuo la compressione creando una ZipEntry e copiando tutti i
            byte dal file sorgente alla zipEntry
          */
          ZipEntry currentEntry = null;
          System.out.println ("Comprimo il file " + files[i].getName ());
          currentEntry = new ZipEntry (files[i].getName());
          
          // aggiungo la ZipEntry allo stream Zip
          zOut.putNextEntry (currentEntry);
          
          // faccio la copia byte a byte
          FileInputStream fIn = new FileInputStream(files[i].getAbsolutePath());
          
          int bTemp;
          while ((bTemp = fIn.read()) != -1) // -1 significa eof End Of File
              zOut.write(bTemp);
          
          // finalizzo la ZipEntry
          zOut.closeEntry();
          fIn.close();
        } // fine dell'if controllo se é un file
      } // file del ciclo su tutti i file
      zOut.close();
      System.out.println("Compressione terminata!!");
    }
    catch (Exception e)
    {
      System.out.println (e);
      System.exit (3);
    }
    
  }
}
