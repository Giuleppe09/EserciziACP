package generator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IDispatcher;

public class Generator{
    public static void main(String[] args) {
        
        System.out.println("[Generator] Avviato.");
        Thread threads[] = new Thread[3];

        try {
            System.out.println("[Generator] Ottengo il riferimento all'RMIRegistry..");
            Registry rmiRegistry = LocateRegistry.getRegistry();

            IDispatcher dispStub = (IDispatcher) rmiRegistry.lookup("dispatcher");

            System.out.println("[Generator] Ho ottenuto lo stub da passare ai thread generatori.");

            for(int i=0;i<3;i++){
                GeneratorRunnable runnable = new GeneratorRunnable(dispStub);
                threads[i] = new Thread(runnable);
                threads[i].start();
            }   

            for(int i=0;i<3;i++){
                threads[i].join();
                System.out.println("[Generator] Joinato il thread numero: "+i);
            }

            


        } catch (RemoteException | NotBoundException e) {
            System.err.println("[Generator] Errore nel servizio RMI.");
            return;
        } catch (InterruptedException e) {
            System.err.println("Errore nella JOIN.");
        }
        

       
    }


}