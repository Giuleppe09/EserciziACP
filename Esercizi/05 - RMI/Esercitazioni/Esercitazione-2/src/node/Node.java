package node;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IDispatcher;

public class Node {
    public static void main(String[] args) {
        
        ThreadWorker[] workers = new ThreadWorker[5];

        System.out.println("[Node] Ricaviamo lo stub dall'RMI Registry.\n");

        try{

            Registry rmiRegistry = LocateRegistry.getRegistry();
        
            IDispatcher stub = (IDispatcher) rmiRegistry.lookup("dispatcher");
            
            System.out.println("[Node] Stub dispatcher ottenuto correttamente, avvio i thread.\n");

            for(int i=0;i<5;i++){
                workers[i] = new ThreadWorker(stub);
                workers[i].start();     
            }  
            
            for(int i=0;i<5;i++){
                try{
                    workers[i].join();                
                }catch(InterruptedException ie){
                    ie.printStackTrace();
                }
            }
        
        }catch(RemoteException re){
            re.printStackTrace();
        }catch(NotBoundException nbe){
            nbe.printStackTrace();
        }

    }
}
