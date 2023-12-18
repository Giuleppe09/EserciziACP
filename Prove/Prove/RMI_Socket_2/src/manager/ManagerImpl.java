package manager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import generator.Order;
import service.IManager;

public class ManagerImpl extends UnicastRemoteObject implements IManager{
    private Vector<RiderProxy> riders;

    protected ManagerImpl() throws RemoteException {
        super();
        riders = new Vector<RiderProxy>();
    }

    @Override
    public void subscribe(int location, int port) throws RemoteException {
        RiderProxy proxy = new RiderProxy(port, location);
        riders.add(proxy);
        System.out.println("[ManagerImpl]: Sottoscrizione del Rider al Manager avvenuta con successo con (location, port) = ("+location+", "+port+")!\n");
    }

    @Override
    public synchronized int setOrder(Order order) throws RemoteException {
        int location = order.getLocation();
        int risultato = -1;
        boolean match = false;
        for(int i=0;i<riders.size();i++){
            if(location==riders.get(i).getLocation()){
                match = true;
                risultato = 1;
                riders.get(i).notifyOrder(order.getId(), order.getAddress());
                System.out.println("[ManagerImpl]: Rider notificato correttamente, ordine preso in carico!\n");
            }
        }
        if(!match){
            System.out.println("[ManagerImpl]: Non è presente alcun rider in zona!\n");
        }
        return risultato;
    }   
}
