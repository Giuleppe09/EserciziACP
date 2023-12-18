package observer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import service.Observer;

public class ObserverImpl extends UnicastRemoteObject implements Observer  {

    
    public ObserverImpl() throws RemoteException{
            super();  
    }

    @Override
    public void observerNotify() throws RemoteException {
     
        System.out.println("Attento che il Server ha detto DioPorco");    
    }

}
