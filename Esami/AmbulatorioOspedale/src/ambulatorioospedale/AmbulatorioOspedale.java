package ambulatorioospedale;

public class AmbulatorioOspedale {

    public static void main(String[] args) {
        // alloco l'oggetto condiviso
        Ambulatorio ambulatorio = new Ambulatorio ();
        
        // creo 12 pazienti
        Paziente pazienti[] = new Paziente[12];
        
        // creo 3 informatori
        Informatore informatori[] = new Informatore[3];
        
        // inizializzo le persone
        for (int i = 0; i < pazienti.length; i++)
            pazienti[i] = new Paziente(ambulatorio,i,"Paziente");
        
        for (int i = 0; i < informatori.length; i++)
            informatori[i] = new Informatore(ambulatorio,i,"Informatore");
        
        // lancio le persone
        for (int i = 0; i < pazienti.length; i++)       pazienti[i].start();
        
        for (int i = 0; i < informatori.length; i++)    informatori[i].start();
        
        // attendo per la terminazione dei pazienti e degli informatori
        try{
            for (int i = 0; i < pazienti.length; i++)     pazienti[i].join();
            for (int i = 0; i < informatori.length; i++)  informatori[i].join();
        }catch (InterruptedException e){
            System.out.println(e);
        }
        
        System.out.println("Simulazione terminata.");
    }
    
}
