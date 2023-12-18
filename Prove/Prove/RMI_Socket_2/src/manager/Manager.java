package manager;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Manager {
    public static void main(String[] args){
        try{
            Registry rmiRegistry = LocateRegistry.getRegistry();
            ManagerImpl manager = new ManagerImpl();
            rmiRegistry.rebind("myManager", manager); 
            System.out.println("[Manager]: Rebind effettuato correttamente!\n");
        }catch(RemoteException e){
            e.printStackTrace();
        }
    }
}
