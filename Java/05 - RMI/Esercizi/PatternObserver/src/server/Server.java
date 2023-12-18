package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.MyService;

public class Server {
    public static void main(String[] args) {

        try{ 
        //Devo inserire il try-catch dell'eccezione RemoteExcteption perchè l'ho trowata nel costruttore ServerImpl()
        //infatti super(), ovvero il costruttore di UnicastRemoteObject può generare quell'eccezione.
            
            System.out.println("[SERVER]Creo l'oggetto remoto");
            MyService myService = new ServerImpl();

            System.out.println("[SERVER] Registro il servizio remoto myService sull'RMI registry.");
            Registry rmiRegistry = LocateRegistry.getRegistry();
            rmiRegistry.rebind("myService", myService);

            System.out.println("[SERVER] Servizio registrato");

        }catch(RemoteException e){
            e.printStackTrace();
        }
    }

}
