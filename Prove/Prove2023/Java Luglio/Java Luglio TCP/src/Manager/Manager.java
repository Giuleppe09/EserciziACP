package Manager;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Service.IManager;

public class Manager {

    public static void main(String[] args) {
        
        try {
            IManager manager = new ManagerImpl();
            Registry rmiRegistry = LocateRegistry.getRegistry();
            rmiRegistry.rebind("manager", manager);

            System.out.println("[Manager] Servizio remoto esportato.");

        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}
