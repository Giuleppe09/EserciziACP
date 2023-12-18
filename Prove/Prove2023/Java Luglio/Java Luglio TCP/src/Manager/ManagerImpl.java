package Manager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.Vector;

import Service.IManager;
import Service.Order;

public class ManagerImpl extends UnicastRemoteObject implements IManager{

    private Vector<Hashtable<String,Integer>> attachedRider = null;
    private Proxy proxy = null;

    protected ManagerImpl() throws RemoteException {
        super();
        this.attachedRider = new Vector<Hashtable<String,Integer>>();
        proxy = new Proxy(0);
    }

    @Override //non avremo problemi di racecondition sull'invocazione del set port
    public synchronized int setOrder(Order ordine) throws RemoteException {
        int result = -1;
        int location = ordine.getLocation();
        for(int i=0;i<attachedRider.size();i++){
            if(location == attachedRider.get(i).get("location")){
                proxy.setPort(attachedRider.get(i).get("port"));
                //Invocazione della notify, che restituirà 1.
                return proxy.notifyOrder(ordine.getId(),ordine.getAddress());  //Invocazione.
            }
        }

        return result;
    }

    @Override
    public void subscribe(int location, int port) throws RemoteException {
        Hashtable<String,Integer> rider = new Hashtable<>();
        rider.put("location", location);
        rider.put("port", port);
        attachedRider.add(rider);

        System.out.println("[ManagerImpl] Aggiunto un rider: "+attachedRider.size());
    }
    
}
