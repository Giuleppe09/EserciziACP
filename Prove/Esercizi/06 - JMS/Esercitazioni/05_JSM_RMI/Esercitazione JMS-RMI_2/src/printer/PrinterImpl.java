package printer;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;

import service.IPrinter;

//Implementiamo l'oggetto remoto per Delega, quindi poi andremo a fare l'export
public class PrinterImpl implements IPrinter{
    private String printerName = null;

    public PrinterImpl(String name){
        this.printerName = name;
    }

    @Override
    public synchronized void  printDoc(String docName) throws RemoteException {
        
        System.out.println("[Printer "+getPrinterName()+"] Richiesta di stampa del documento: "+docName+"\n");

        try {
            FileWriter fw = new FileWriter("documenti.txt",true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            Thread.sleep(5000);
            pw.append("DocName: "+docName+"\n");
            pw.flush();

            System.out.println("[Printer "+getPrinterName()+"] Stampa avvenuta con successo!\n");

            pw.close();
            bw.close();
            fw.close();

        
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }catch(InterruptedException ie){
            ie.printStackTrace();
        }


    }
    
    public String getPrinterName(){
        return printerName;
    }

    
}
