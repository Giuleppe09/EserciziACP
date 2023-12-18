package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import service.MyService;
import service.Observer;

public class ServerImpl extends UnicastRemoteObject implements MyService {

    //Contiene gli observer che hanno effettuato l'attach
    private Vector<Observer> attachedObservers; 

    public ServerImpl() throws RemoteException {
        super(); //Viene chiamato il costruttore di UnicastRemoteObject
        
        attachedObservers = new Vector<Observer>();
    }



    @Override
    public void service_method() throws RemoteException {
        
        System.out.println("Dio Maiale.");
        notifyAllObserver();
        
    }

    @Override
    public void attachObserver(Observer observer) throws RemoteException {
        System.out.println("\n Nuovo observer attached!");
        attachedObservers.add(observer);
    }


    private void notifyAllObserver(){
        System.out.println("[SERVER] Notifico tutti gli Observer");
        for(int i=0; i < attachedObservers.size(); i++ ){
            try {
                
                attachedObservers.get(i).observerNotify();  //Invoco la CallBack.

            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
