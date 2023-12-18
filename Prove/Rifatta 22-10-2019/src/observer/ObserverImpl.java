package observer;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.rmi.RemoteException;

import service.IDispatcher;
import service.IObserver;
import service.Reading;

public class ObserverImpl implements IObserver{

    private String nameFile =null;
    private IDispatcher stub = null;
    

    public ObserverImpl(String nomeFile, IDispatcher dispStub) {
        this.nameFile=nomeFile;
        this.stub = dispStub;
    }

    @Override
    public void notifyReading() throws RemoteException {
        
        Reading lettura = this.stub.getReading();
        System.out.println("[Observer] Ricevuto la lettura.");



        FileWriter fw;
        try {
            fw = new FileWriter(nameFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.append(Integer.toString(lettura.getValore())+"\n");
            pw.flush();

            pw.close();
            bw.close();
            fw.close();
        
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
}
