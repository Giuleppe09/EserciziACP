package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IManager extends Remote {

    public void subscribe(int location,int port) throws RemoteException; //attach invocato dai rider
    public int setOrder(Order ordine) throws RemoteException; //Metodo invocato dai threadGeneratori.
    
}