package service;

import java.io.Serializable;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IObserver extends Remote,Serializable{ //Serializable perchè sarà parametro dell'attach!

    public void notifyReading() throws RemoteException;

} 