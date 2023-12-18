package Interfacce;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IObserver extends Remote{

    //Metodo di Callback.
    public void notifyObserver() throws RemoteException;
    
}