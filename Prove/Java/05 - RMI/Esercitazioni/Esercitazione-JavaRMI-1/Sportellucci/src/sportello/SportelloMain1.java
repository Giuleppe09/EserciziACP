package sportello;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import service.IGestoreSportelli;
import service.ISportello;

public class SportelloMain1 {
    public static void main(String[] args) {    //Qui andremo ad attaccare gli sportelli pens...

        //Creazione dei 3 sportelli...
        ISportello[] sportelli = new ISportello[3];
    
        System.out.println("[SportelloMain] Avvio la creazione degli sportelli..");

        for(int i=0;i<3;i++){
            try {
                sportelli[i] = new SportelloImpl(i);
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        try{
                    //Otteniamo lo stub per chiamare l'attach degli sportelli
        Registry rmiRegistry = LocateRegistry.getRegistry();
        IGestoreSportelli stub = (IGestoreSportelli) rmiRegistry.lookup("gestoreSportelli");
            
        
        for(int i=0;i<3;i++){
            
            ISportello stubSportello =(ISportello)UnicastRemoteObject.exportObject(sportelli[i],0);
            System.out.println("nenene");
            stub.attachSportello(stubSportello);
             
          
            System.out.println("[SportelloMain] Sportello "+i+" attached!");

        }

   
        }catch(RemoteException ex){
            ex.printStackTrace();
        }catch(NotBoundException ex){
            System.err.println("[SportelloMain] Problema nel ricavare lo stub associato al nome logico: GestoreSportelli");
        }

    }
    
}
