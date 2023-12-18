package dispatcher;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import javax.jms.JMSException;
import javax.jms.MapMessage;

import service.IPrinter;

public class DispatcherThread extends Thread{
    MapMessage mex = null;

    public DispatcherThread(MapMessage message) {
        super();
        mex = message;
    }

    public void run(){
        try {
            
            String printerName = mex.getString("nomePrinter");
            String docName = mex.getString("nomeDocumento");

            Registry rmiRegistry = LocateRegistry.getRegistry();
            IPrinter stub = (IPrinter) rmiRegistry.lookup(printerName);
            
            stub.printDoc(docName);


        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    
}
