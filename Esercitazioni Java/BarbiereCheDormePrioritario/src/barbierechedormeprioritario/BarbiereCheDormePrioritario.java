/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package barbierechedormeprioritario;

public class BarbiereCheDormePrioritario {

    public static void main(String[] args) {
        Negozio negozio = new Negozio(15); // 10 sedie disponibili nel negozio
        Barbiere barbiere = new Barbiere(negozio);
        Stampatore stampatore = new Stampatore (negozio);
        
        // creo l'array di clienti
        Cliente clienti[] = new Cliente[30];
        
        // istanzio tutti i clienti
        for (int i = 0; i < clienti.length; i++)
            clienti[i] = new Cliente(negozio,i);
        
        // lancio i thread
        stampatore.start();
        barbiere.start();
        for (int i = 0; i < clienti.length; i++)
            clienti[i].start();
        
        // attendiamo per la terminazione dei clienti
        for (int i = 0; i < clienti.length; i++)
            try{
                clienti[i].join();
            }catch (InterruptedException e){
                System.out.println(e);
            }
        
        // genero l'interrupt per terminare il barbiere
        barbiere.interrupt();
        
        // mi metto in attesa per la terminazione del barbiere
        try{
            barbiere.join();
        }catch (InterruptedException e){
        }
        
        // termino lo stampatore
        stampatore.interrupt();
        
        try{
            stampatore.join();
        }catch (InterruptedException e){
            System.out.println (e);
        }
        System.out.println("Simulazione terminata....");
        
        // svuoto la stringa di output sulla shell
        negozio.svuotaOutput();
        
        // stampo i tempi medi d'attesa per tutti i clienti
        for (int i = 0; i < clienti.length;i++){
            System.out.println (clienti[i].getName()+
                                " lungh. cap."+
                                clienti[i].getLunghezzaCapelli()+
                                " tempo attesa: "+
                                clienti[i].getTempoMedioAttesa());
        }
    }
}
