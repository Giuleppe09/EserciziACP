package generator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IManager;

public class Generator{
    public static void main(String[] args) {
        
        GeneratorThread[] threads = new GeneratorThread[3]; 
        System.out.println("[GeneratorMain] Avviato.");

        try {
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IManager stub = (IManager) rmiRegistry.lookup("manager");

            System.out.println("[GeneratorMain] Ottenuto lo stub RMI manager.");

            for(int i=0;i<3;i++){
                threads[i] = new GeneratorThread(stub);
                threads[i].start();
            }
            System.out.println("[GeneratorMain] ThreadGeneratori creati e avviati.");

            for(int i=0;i<3;i++){
                try {
                    threads[i].join();
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            System.out.println("[GeneratorMain] FINE");
        
        }catch (RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        



    }
}