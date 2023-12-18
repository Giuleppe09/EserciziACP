package manager;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IManager;

public class Manager {
    public static void main(String[] args) {
        
        try {
            System.out.println("[ManagerMain] Avviato.");
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IManager managerImpl = new ManagerImpl();
            rmiRegistry.rebind("manager", managerImpl);
            
            System.out.println("[ManagerMain] Servizio remoto esportato e pronto per essere utilizzato.");

        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
         

    }
}
