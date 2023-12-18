package manager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.Vector;

import service.IManager;
import service.Order;

//Implementato per ereditarietà ja.

public class ManagerImpl extends UnicastRemoteObject implements IManager{

    private Vector<Hashtable<String,Integer>> attachedRider = null; 
    private ProxyManager proxy = null;

    protected ManagerImpl() throws RemoteException {
        super();
        attachedRider = new Vector<>();
        proxy = new ProxyManager();
    }

    @Override
    public void subscribe(int location,int port) throws RemoteException {
        System.out.println("[ManagerImpl] Richiesta di attach di un rider.\n");
        Hashtable<String,Integer> riderInfo = new Hashtable<>();
        riderInfo.put("location", location);
        riderInfo.put("portRider", port);

        attachedRider.add(riderInfo);
        System.out.println("[ManagerImpl] Rider attached: \n \t Location: "+ riderInfo.get("location") + "PortRider: "+riderInfo.get("portRider"));
    }

    @Override
    public synchronized int setOrder(Order ordine) throws RemoteException{
        int location = ordine.getLocation();
        System.out.println("[ManagerImpl] E' stato invocato un setOrder dell'ordine con location: "+ ordine.getLocation());
        for(int i=0;i<attachedRider.size();i++){
            if(location == attachedRider.get(i).get("location")){
                System.out.println("[ManagerImpl] Notifico il rider registrato con port: "+attachedRider.get(i).get("portRider"));
                proxy.setPortInvio(attachedRider.get(i).get("portRider"));
                return proxy.notifyOrder(ordine.getIdOrder(), ordine.getAddress());
            }   
        }
        System.out.println("[ManagerImpl] Non ci sono Rider interessati.");
        return -1;
    }

}
