package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

/*
 * Espone il servizio remoto invocabile dal Client (service_method)
 * e espone il metodo attachObserver invocabile dagli Observer per effettuare l'attach al Subject 
 * in modo da implementare il Pattern comportamentale 'Observer'.
 */

public interface MyService extends Remote{
    
    public void attachObserver(Observer observer) throws RemoteException;
    public void service_method() throws RemoteException; //Stamperà a video una stringa.

}