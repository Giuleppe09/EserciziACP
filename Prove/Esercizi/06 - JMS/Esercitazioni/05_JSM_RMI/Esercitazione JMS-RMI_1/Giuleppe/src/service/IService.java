package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

//Estendiamo la classe Remote, dato che verrà implementata da un oggetto che verrà invocato da remoto
public interface IService extends Remote{
    public void deposita(int val) throws RemoteException;
    public int preleva() throws RemoteException;
}
