package generator;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IManager;

public class Generator{
    public static void main(String[] args){
        try{
            Registry rmireRegistry = LocateRegistry.getRegistry();
            IManager stub = (IManager) rmireRegistry.lookup("myManager");

            clientT[] workers = new clientT[3];

            for(int i=0;i<3;i++){
                workers[i] = new clientT(stub);
                workers[i].start();
            }

            for(int i=0;i<3;i++){
                try {
                    workers[i].join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }catch(RemoteException e){
            e.printStackTrace();
        }catch(NotBoundException e){
            e.printStackTrace();
        }
    }
}