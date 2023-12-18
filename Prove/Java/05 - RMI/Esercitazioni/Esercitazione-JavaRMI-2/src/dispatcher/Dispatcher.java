package dispatcher;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IDispatcher;

/*
 * Nel contesto Client-Dispatcher, il Dispatcher funge da Server.
 * Nel contesto Dispatcher-Printer, il dispatcher funge da Client.
 */

public class Dispatcher { //Main
    public static void main(String[] args) {

        //Pubblicare il servizio sull'RMI Registry.

        System.out.println("[Dispatcher] Avvio del Dispatcher.");

        try {
            
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IDispatcher dispatcher = new DispatcherImpl();
            
            System.out.println("[Dispatcher] Pubblicazione del servizio remoto 'myDispatcher' in corso..");
            rmiRegistry.rebind("myDispatcher", dispatcher);
            System.out.println("[Dispatcher] Servizio pubblicato correttamente!");

        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
