package Rider;

import java.rmi.AccessException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Service.IManager;

public class RiderMain {

    public static void main(String[] args) {
        
        int locationInteresse = Integer.parseInt(args[0]);
        int port = Integer.parseInt(args[1]);
        String nomefile = args[2];

        RiderImpl rider = new RiderImpl(port,nomefile);
        Registry registry;
        
        
        try {
            registry = LocateRegistry.getRegistry();
            IManager stub = (IManager) registry.lookup("manager");
            
            stub.subscribe(locationInteresse, port);
            rider.runSkeleton();
        
            
        } catch (AccessException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
                

    }
    
}
