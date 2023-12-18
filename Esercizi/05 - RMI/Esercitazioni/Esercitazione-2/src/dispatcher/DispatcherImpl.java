
package dispatcher;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import service.IDispatcher;
import service.IPrinter;

public class DispatcherImpl extends UnicastRemoteObject implements IDispatcher{
    private Vector<IPrinter> attachedPrinters = null;

    protected DispatcherImpl() throws RemoteException {
        super();
        attachedPrinters = new Vector<IPrinter>();
        //TODO Auto-generated constructor stub
    }

    @Override
    public void addPrinter(IPrinter printer) throws RemoteException { //Metodo che verrà invocato dalle Printer.
        attachedPrinters.add(printer);
        System.out.println("[Dispatcher] Printer addata correttamente.\n");
    }  

    @Override
    public boolean printRequest(String docName) throws RemoteException {
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