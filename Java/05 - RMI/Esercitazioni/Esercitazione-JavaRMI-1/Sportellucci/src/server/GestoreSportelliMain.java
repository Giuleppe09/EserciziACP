package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IGestoreSportelli;

public class GestoreSportelliMain {
    public static void main(String[] args) {
        
        //GestoreSportelli è un servizio remoto implementato per ereditarietà.
        System.out.println("[GestoreSportelliMain] GestoreSportelli avviato..");
        try {
            IGestoreSportelli gestoreSportelli = new GestoreSportelliImpl();
            Registry rmiRegistry = LocateRegistry.getRegistry();
            rmiRegistry.rebind("gestoreSportelli", gestoreSportelli);

        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }
    
}
