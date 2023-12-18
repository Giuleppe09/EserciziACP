package observer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import service.Observer;

import service.MyService;

public class ObserverClient {
    public static void main(String[] args) {
        
        Registry rmiRegistry;
        try {
            
            rmiRegistry = LocateRegistry.getRegistry();
            Observer obs = new ObserverImpl();
            
            MyService stub = (MyService)rmiRegistry.lookup("myService");
            stub.attachObserver(obs);


        } catch (RemoteException e) {
             // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
                e.printStackTrace();
            }

    }
}
