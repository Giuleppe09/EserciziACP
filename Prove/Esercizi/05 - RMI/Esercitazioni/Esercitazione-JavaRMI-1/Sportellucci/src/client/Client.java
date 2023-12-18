package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IGestoreSportelli;

public class Client {
    public static void main(String[] args) {

        int T=10, R=10;
        
        ThreadClient[] threadWorkers = new ThreadClient[T];

        try{

            Registry rmiRegistry = LocateRegistry.getRegistry();
            IGestoreSportelli stubGestoreSportello = (IGestoreSportelli) rmiRegistry.lookup("gestoreSportelli");

            System.out.println("[Client] Avvio "+T+" ThreadClient.\n");
            
            for(int i = 0; i < T ;i++){
             
                threadWorkers[i] = new ThreadClient(R,stubGestoreSportello);
                threadWorkers[i].start();
            
            }
            
            for(int i=0;i<T;i++){
                try{
                    threadWorkers[i].join();
                }catch(InterruptedException ie){
                    ie.printStackTrace();
                }
            }


        }catch(RemoteException ex){
            ex.printStackTrace();
        }catch(NotBoundException nbe){
            nbe.printStackTrace();
        }


    }
}
