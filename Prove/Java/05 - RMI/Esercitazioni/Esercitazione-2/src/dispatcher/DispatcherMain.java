package dispatcher;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IDispatcher;

public class DispatcherMain {
    public static void main(String[] args) {
        
        try{
            IDispatcher dispatcher = new DispatcherImpl();
            Registry rmiRegistry = LocateRegistry.getRegistry();
            
            rmiRegistry.rebind("dispatcher", dispatcher);
            System.out.println("[ServerDispatcher] Servizio remoto dispatcher registrato correttamente\n");
        }catch(RemoteException re){
            re.printStackTrace();
        }
    }
}
