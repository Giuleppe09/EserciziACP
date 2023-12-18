package service;

import java.rmi.RemoteException;
import java.rmi.Remote;


public interface IDispatcher extends Remote{
    public void addPrinter(IPrinter printer) throws RemoteException; //Permette il meccanismo di print sfruttando callback distribuita basata su socket però.
    public boolean printRequest(String docName) throws RemoteException;    


}
