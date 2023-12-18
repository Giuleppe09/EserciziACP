package dispatcher;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import service.IDispatcher;
import service.IPrinter;


/*
 * Quest'implementazione funzionerà da intermediario tra ThreadClient - Printer.
 * Implementiamo i servizi che verranno invocati tramite RMI, addPrinter e PrintRequest.
 */

public class DispatcherImpl extends UnicastRemoteObject implements IDispatcher{

    private Vector<IPrinter> attachedPrinters; //Vettore delle 'stampanti' associate al dispatcher.

    protected DispatcherImpl() throws RemoteException {
        super();
        attachedPrinters = new Vector<IPrinter>();

    }


    /*
     * Da come diceva l'esercizio, la comunicazione tra Dispatcher-Printer deve avvenire solo tramite pattern Proxy-Skeleton.
     * Per facilitare, pubblicando il servizio su RMI Registry allora possiamo far invocare alle stampanti questo metodo tramite RMI.
     */
    public void addPrinter(int port) throws RemoteException{ 
        System.out.println("[Dispatcher] Aggiunta di una stampante...");
        
        IPrinter printer = new Proxy(port); //Costruiamo il proxy associato alla stampante.
        attachedPrinters.add(printer);

        System.out.println("[Dispatcher] Stampante aggiunta correttamente!");
    }

    @Override

    //Questo metodo invocherà il metodo print sui Proxy associati alle stampanti che hanno effettuato l'addPrinter.
    public boolean printRequest(String docName) throws RemoteException{ //E' ovviamente invocato tramite RMI dai ThreadClient ovviamente.

        boolean result = false;
        System.out.println("[Dispatcher] Richiesta di stampa del documento ["+docName+"].");

        for(int i=0;i< attachedPrinters.size() && (!result);i++){

            result = attachedPrinters.get(i).print(docName); //Vai in Proxy per vedere come è implementato l'invio della richiesta di print.

            if(result){
                System.out.println("[Dispatcher] Richiesta di stampa del documento ["+docName+"] eseguita correttamente dalla stampante ["+i+"] :)\n");
            }
        }


        
        if(!result){
            System.out.println("[Dispatcher] Richiesta di stampa del documento ["+docName+"] non è stata evasa. :( \n");
        }   
        


       return result;

    }

    
    
}
