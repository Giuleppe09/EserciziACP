package printer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Semaphore;

import service.IPrinter;



public class PrinterImpl implements IPrinter{

    private Semaphore mutex;
    private String documentoSuCuiStampare;


    //Avremo un documento diverso su cui stampare per ogni stampante!
    public PrinterImpl(String documento){
        mutex = new Semaphore(1);//1 sola stampa alla volta!
        documentoSuCuiStampare=documento; 
    }
    
    @Override
    public boolean print(String docName) { // è quello da scrivere sul documentoSuCuiStampare

        if(!mutex.tryAcquire()){ //Tentiamo di acquisire il permesso, se è già occupato ritorna false!
            return false;
        }

        try {
            
            FileWriter fw = new FileWriter(documentoSuCuiStampare,true); //Permette la scrittura di caratteri in un file.
            BufferedWriter bw = new BufferedWriter(fw); //fornisce un buffer per migliorare la prestazione della scrittura su file.
            PrintWriter pw = new PrintWriter(bw); //semplifica la scrittura di testo su un flusso di output.

            pw.append(docName + "\n");
            pw.flush(); //per assicurarsi che tutti i dati siano scritti nel file immeediatamente

            pw.close();
            bw.close();
            fw.close();


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //Scrittura su file
        System.out.println("[Printer] Scrittura..");



        return true;
        
    }
    

}
