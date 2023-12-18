package manager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;
import java.util.Vector;

import service.AlertNotification;
import service.IManager;

public class ManagerImpl extends UnicastRemoteObject implements IManager{

    private Vector<Hashtable<String,Integer>> attachedSubscriber = null;
    private Proxy proxy = null;

    protected ManagerImpl() throws RemoteException {
        super();
        attachedSubscriber = new Vector<Hashtable<String,Integer>>();
        proxy = new Proxy(0);
    }

    @Override
    public synchronized void sendNotification(AlertNotification notification) throws RemoteException {
        int componentID = notification.getComponentID();

        for(int i=0;i<attachedSubscriber.size();i++){
            if(attachedSubscriber.get(i).get("componentId")==componentID){
                int port = attachedSubscriber.get(i).get("portSubscriber");
                
                System.out.println("[ManagerImpl] Verrà contattato il subscriber in ascolto sul port: "+port+".\n");
                
                proxy.setPortSubscriber(port);
                proxy.notifyAlert(notification.getComponentID());

            }
        }

    }


    @Override
    public void subscribe(int componentIDInteresse, int portSubscriber) throws RemoteException {
        System.out.println("[ManagerImpl] Ricevuta una richiesta di attach dal subscriber interessato al componentID:"+componentIDInteresse+" in attesa di notifiche sul port:"+portSubscriber);
        Hashtable<String,Integer> sub = new Hashtable<>();
        sub.put("componentId", componentIDInteresse);
        sub.put("portSubscriber", portSubscriber);

        attachedSubscriber.add(sub);
        
        System.out.println("[ManagerImpl] Dim vettore: "+attachedSubscriber.size());
        System.out.println("[ManagerImpl] Subscriber correttamente registrato.");
        
    }
    
}
