package observer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IDispatcher;
import service.IObserver;

public class Observer {
    
    public static void main(String[] args) {
        if(args.length!=2){
            System.out.println("[ObserverMain] Errore, devi testare con la stringa: java nomePackage.Observer temperatura lettureTemperatura.txt ");
            return;
        }

        String tipoLettureOsservate = args[0];
        String nomeFile = args[1];
    
        Registry rmiRegistry;
        
        try {
        
            rmiRegistry = LocateRegistry.getRegistry();
            IDispatcher dispStub= (IDispatcher) rmiRegistry.lookup("dispatcher");
            IObserver observer = new ObserverImpl(nomeFile, dispStub);
            dispStub.attachObserver(observer, tipoLettureOsservate);

            while (true) {
                            
            }
            
        } catch (RemoteException e) {
            e.printStackTrace();
            System.err.println("[Observer] Errore nel ricavare l'RMI Registry.");
        } catch (NotBoundException e) {
            e.printStackTrace();
            System.err.println("[Observer] Errore nel ricavare lo stub.");
        }
    }
}
