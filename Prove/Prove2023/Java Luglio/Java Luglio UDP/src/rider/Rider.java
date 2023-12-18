import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IManager;
import service.IRider;

public class Rider {
    public static void main(String[] args) {

        if(args.length!=3){
            return;
        }

        int loc = Integer.parseInt(args[0]);
        int port = Integer.parseInt(args[1]);
        String nomeFile = args[2];

        try {
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IManager managerstub = (IManager) rmiRegistry.lookup("manager");
            IRider riderImpl = new RiderImpl(port, nomeFile);
            managerstub.subscribe(loc, port);
            System.out.println("[Rider] Registrato correttamente, con location: "+loc+" port: "+port);
            
            RiderSkeleton skeleton = new RiderSkeleton(port, riderImpl);
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
