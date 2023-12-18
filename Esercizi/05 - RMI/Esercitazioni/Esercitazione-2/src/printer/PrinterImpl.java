package printer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.Semaphore;

import service.IPrinter;

public class PrinterImpl implements IPrinter{
    
    private int port;
    private String docNameStamp; //Su cui stampiamo
    private Semaphore mutex;


    public PrinterImpl(int port, String doc){
        this.port = port;
        this.docNameStamp = doc;
        mutex = new Semaphore(1);
    }

    

    @Override
    public boolean print(String docName) {
        boolean result = false;

        if(!mutex.tryAcquire()){
            System.out.println("[Printer] Impossibile ottenere il mutex, è occupato.");
            return result;
           
        }

        try{

            FileWriter fw = new FileWriter(docNameStamp,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.append("Nome file: "+docName);
            pw.flush();

            pw.close();
            bw.close();
            fw.close();

            result=true;
            System.out.println("Stampa avvenuta con successo. Doc: "+docName+"\n");
            
        }catch(IOException ioe){
            ioe.printStackTrace();
        }finally{
            mutex.release();
        }
        return result;
    }

    public int getPort(){
        return port;
    }
    
}
