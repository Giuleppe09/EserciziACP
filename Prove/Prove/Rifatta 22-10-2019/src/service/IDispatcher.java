package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IDispatcher extends Remote{

    public void setReading(Reading reading) throws RemoteException; //Invocabile dai thread generator
    
    public void attachObserver(IObserver callbackObject,String letturaInteresse) throws RemoteException; //Invocabile da Observer.
    //Lettura interesse sarebbero le letture a cui è interessato l'observer.

    public Reading getReading() throws RemoteException; //Invocato dall'observer a seguito di una notify.
}