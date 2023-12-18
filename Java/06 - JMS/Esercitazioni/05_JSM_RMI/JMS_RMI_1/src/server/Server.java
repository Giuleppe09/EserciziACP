package server;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import coda.Coda;
import coda.CodaWrapperSemaphore;
import service.IService;

public class Server {

    public static void main(String[] args){

        //Implementa il servizio RMI, che espone i metodi preleva e deposito.
        //Dimensione della coda pari a 5
        Registry rmiRegistry;
		
        try {
            
			rmiRegistry = LocateRegistry.getRegistry();
            IService obj = new ServerImpl(new CodaWrapperSemaphore(new Coda(4)));
            rmiRegistry.rebind("service", obj);
        
        } catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
}
