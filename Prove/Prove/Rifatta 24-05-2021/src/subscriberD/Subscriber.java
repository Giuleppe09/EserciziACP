package subscriberD;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import service.IManager;
import service.ISubscriber;


public class Subscriber{

    public static void main(String[] args) {
        
        if(args.length!=3){
            System.err.println("[SubscriberMain] Devi eseguire con questa stringa: "+"java subscriber.Subscriber 3 8000 alert.txt .");
            return;
        }


        int idInteresse = Integer.parseInt(args[0]);
        int portSubscriber = Integer.parseInt(args[1]);
        String nomeFile = args[2];

        try {
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IManager stub = (IManager) rmiRegistry.lookup("manager");
            

            System.out.println("[SubscriberMain] Attach..");
            stub.subscribe(idInteresse, portSubscriber);
            System.out.println("[SubscriberMain] Attach eseguito..");

            ISubscriber subscriberImpl = new SubscriberImpl(idInteresse, nomeFile);
            SubscriberSkeleton skeleton = new SubscriberSkeleton(subscriberImpl, portSubscriber);
            skeleton.runSkeleton();

        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}