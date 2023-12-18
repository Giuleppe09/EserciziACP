package Server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Interfacce.IWhiteBoard;

public class Server {
    public static void main(String[] args) {
        
        try {
            //Creo e registro l'oggetto remoto
        
            IWhiteBoard whiteboard = new WhiteBoardImpl();
            
            Registry rmiRegistry = LocateRegistry.getRegistry();
            rmiRegistry.rebind("whiteboard", whiteboard);
        
        } catch (RemoteException e) {
         
            e.printStackTrace();
        }
        
    }
    
}
 