package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.Hello;

public class HelloClient {

    private HelloClient() {}


    /*
     * L'host del Server RMI può essere specificato anche come 
     * argomento di getRegistry(host), magari passato per argomento quando
     * il Server RMI è in esecuzione su una macchina diversa rispetto
     * al Client.
     * 
     * Se l'host non è specificato, il metodo si connetterà al registro RMI
     * sul localhost.
     */

    public static void main(String[] args) {

        /*
        *Questa stringa è utilizzata per prendere da terminale
        *l'IP a cui connettersi, nel caso in cui non è localhost.
        *String host = (args.length < 1) ? null : args[0];
        */
        
        try { //Ottenere il riferimento all'oggetto Hello remoto.
            

           /* 
            * Specificare l'host per fare il lookup nel caso in cui
            * il Server RMI non sia su localhost
            * Registry registry = LocateRegistry.getRegistry(host);
            */ 
            
            Registry registry = LocateRegistry.getRegistry();
            
            //Viene ottenuto il riferimento all'oggetto remoto "Hello", cercandolo sul registro RMI
            Hello stub = (Hello) registry.lookup("Hello"); 
        //Questo ritorna un riferimento di tipo Hello, che è un'interfaccia remota che estende Remote



            
            // invocazione del metodo remoto sayHello()
            String response = stub.sayHello();
            System.out.println("response: " + response);
        
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}