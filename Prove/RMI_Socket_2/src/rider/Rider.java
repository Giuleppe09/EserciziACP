package rider;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IManager;

public class Rider {
    public static void main(String[] args){
        try{
            if(args.length!=3){
                System.out.println("[Rider]: Command Line Arguments -> Location Port NomeFile!\n");
            }
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IManager stub = (IManager) rmiRegistry.lookup("myManager");
            int location = Integer.parseInt(args[0]);
            if(!(location>0 && location<6)){
                System.out.println("[Rider]: La location deve essere compresa tra 1 e 5, inseriscila nuovamente!\n");
                System.out.println("[Rider]: Per favore interrompi manualmente con Ctrl+C e reimmetti una location valida!\n");
            }
            int port = Integer.parseInt(args[1]);
            String nomeFile = args[2];
            stub.subscribe(location,port);

            RiderImpl rider = new RiderImpl(nomeFile);
            RiderSkeleton skel = new RiderSkeleton(port, rider);
            skel.runSkeleton();
        }catch(RemoteException e){
            e.printStackTrace();
        }catch(NotBoundException e){
            e.printStackTrace();
        }
    }
}
