package Generator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Service.IManager;

public class Generator{
    public static void main(String[] args) {
        ClientT[] threads = new ClientT[3];
        try {
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IManager stub = (IManager) rmiRegistry.lookup("manager");

            System.out.println("[Generator] Ho acquisito il riferimento all'oggetto remoto Manager.");

            for(int i=0;i<3;i++){
                //Genera 3 thread, ciascuno invoca setOrder su ManagerRMI
                threads[i] = new ClientT(stub);
                threads[i].start();
            }

            for(int i =0; i<3;i++){
                threads[i].join();
            }

        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}