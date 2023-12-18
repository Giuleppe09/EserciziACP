package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ISportello extends Remote{
    
    //Metodo di Callback, invocato dal Gestore sportelli..
    public boolean serviRichiesta(int idCliente) throws RemoteException;

    public int getId() throws RemoteException; //Metodo invoabile da uno sportello.

    
} 