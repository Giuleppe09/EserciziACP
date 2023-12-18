package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * Interfaccia remota che espone i metodi che verranno implementati
 * in ObserverImpl, e che verrà invocato dal Server quando c'è un evento  di invocazione
 * di service_method(). 
 * 
 * E' anche detta InterfacciaCallback.
 * 
 * L'ObserverClient creerà un oggetto Observer il cui metodo verrà invocato 'Remotamente'
 * lato Server.
 */

public interface Observer extends Remote{
    
    //Metodo di Callback
    public void observerNotify() throws RemoteException;
}
