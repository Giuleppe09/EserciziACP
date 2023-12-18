package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IDispatcher;

public class node{
    public static void main(String[] args) {
        
        ClientThread[] threads = new ClientThread[5];
        
        try {

            Registry rmiRegistry = LocateRegistry.getRegistry();
            IDispatcher dispatcher = (IDispatcher) rmiRegistry.lookup("myDispatcher"); 
            System.out.println("[Node] Servizio myDispatcher ricavato dall'RMI Registry!");


            for(int i=0;i<5;i++){

                threads[i] = new ClientThread(dispatcher);
                threads[i].start();
                System.out.println("[Node] Thread("+i+") creato e startato.");
            
            }

            for(int i=0;i<5;i++){
                threads[i].join();
            }


            

        } catch (RemoteException e) {
            System.out.println("[NODE] Impossibile contattare l'RMI Registry!");
        }catch(NotBoundException e){
            System.out.println("[NODE] Servizio 'myDispatcher' non presente nell'RMI Registry");
        }catch(InterruptedException e){
            System.err.println("[Node] Errore nella join!");
        }
        
    }
}