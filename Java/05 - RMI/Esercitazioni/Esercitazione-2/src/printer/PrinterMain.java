package printer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import dispatcher.Proxy;
import service.IDispatcher;
import service.IPrinter;

public class PrinterMain {
    public static void main(String[] args) {
        
        int port = Integer.parseInt(args[0]);
        String doc = args[1];

        IPrinter printer = new PrinterImpl(port,doc);
        Skeleton skeleton = new Skeleton(printer);
        

        try{
            
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IDispatcher stub = (IDispatcher)rmiRegistry.lookup("dispatcher");
            
            IPrinter printerProxy = new Proxy(printer);

            stub.addPrinter(printerProxy); //Cioè facciamo l'attach del proxy direttamente
            System.out.println("Printer attached!");
            skeleton.runSkeleton();

        }catch(RemoteException re){
            re.printStackTrace();
        }catch(NotBoundException nbe){
            nbe.printStackTrace();
        }

    }
    
}
