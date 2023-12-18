package dispatcher;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import service.IDispatcher;

public class Dispatcher {
    public static void main(String[] args) {

    try {
        Registry rmiRegistry = LocateRegistry.getRegistry();
        System.out.println("[Dispatcher] Ottengo il registro rmi e registro l'oggetto remoto.");

        DispatcherImpl dispatcher =  new DispatcherImpl();
        IDispatcher stub = (IDispatcher) UnicastRemoteObject.exportObject(dispatcher, 0);
        rmiRegistry.rebind("dispatcher",stub);

        System.out.println("[Dispatcher] Oggetto remoto correttamente esportato e pronto per l'utilizzo.");

    } catch (RemoteException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }

    }
}
