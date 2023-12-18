package manager;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IManager;

public class Manager {
    public static void main(String[] args) {
        
        try {
            System.out.println("[ManagerMain] Avviato correttamente");

            Registry rmiRegistry = LocateRegistry.getRegistry();
            IManager manager = new ManagerImpl();
            rmiRegistry.rebind("manager", manager);
            System.out.println("[Manager] Oggetto remoto 'manager' esportato correttamente!");

        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }
}
