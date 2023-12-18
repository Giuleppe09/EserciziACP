package generator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IManager;

public class Generator{
    public static void main(String[] args) {
        
        System.out.println("[GeneratorMain] Avviato.");
        
        try {
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IManager stub = (IManager) rmiRegistry.lookup("manager");
            System.out.println("[GeneratorMain] Ho ricavato lo stub da dare ai thread.");
            
            ComponentThread threads[] = new ComponentThread[3];

            for(int i=0;i<threads.length;i++){

                threads[i] = new ComponentThread(stub,"Component["+i+"]");
                threads[i].start();
            
            }

            for(int i=0;i<threads.length;i++){
                threads[i].join();
            }

            System.out.println("[GeneratorMain] FINE");

        } catch (RemoteException | NotBoundException e) {
            System.err.println("Errore di natura RMI");
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }
}