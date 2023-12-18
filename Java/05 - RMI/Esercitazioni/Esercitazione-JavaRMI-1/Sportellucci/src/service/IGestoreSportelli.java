package service;

import java.rmi.Remote;
import java.rmi.RemoteException;



public interface IGestoreSportelli extends Remote{


    public boolean sottoponiRichiesta(int idCliente) throws RemoteException;       //Metodo che verrà invocato dai client.
    public void attachSportello(ISportello sportello) throws RemoteException; //Verrà invocato dagli sportelli.
}
