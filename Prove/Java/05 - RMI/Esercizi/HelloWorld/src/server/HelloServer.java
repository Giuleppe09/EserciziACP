package server;
        
import java.rmi.registry.Registry;
import java.rmi.registry.LocateRegistry;

public class HelloServer {
        
        
    public static void main(String args[]) {
        
        try {

            //HelloServerImplDelega obj_d = new HelloServerImplDelega();
            HelloServerImplEreditarieta obj_e = new HelloServerImplEreditarieta(); 
            
            //Se fosse implementato per Delega:
            //Hello stub = (Hello) UnicastRemoteObject.exportObject(obj_d, 0);

            
            //Viene ottenuto un riferimento al registro RMI.
            Registry registry = LocateRegistry.getRegistry();
            
            /*
             * L'oggetto HelloServerImplEreditarieta è associato al nome "Hello"
             * nel registro RMI.
             * 
             * Usando il metodo registry.bind(..), e nel registro esiste già un oggetto
             * con quel nome, allora il metodo ritorna un'eccezione AlreadyBoundException.
             * Rebind invece sovrascrive anche le associazioni esistente, dunque non abbiamo
             * problemi relativi all'eccezione AlreadyBoundException.
             * 
             */
            registry.rebind("Hello", obj_e);

            System.err.println("Server ready");

        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }
}