package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.MyService;

public class Invoker {

    public static void main(String[] args) {
        
        try {
            System.out.println("[INVOKER] Ottengo l'RMI Registry");
            Registry rmiRegistry = LocateRegistry.getRegistry();

            System.out.println("[INVOKER] Ottengo il servizio remoto myService");
            MyService stub =(MyService) rmiRegistry.lookup("myService");

            System.out.println("[INVOKER] Invoco il servizio remoto");
            stub.service_method();


        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (NotBoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        
    }

}
