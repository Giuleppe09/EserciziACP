package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IManager extends Remote{

    public void sendNotification(AlertNotification notification) throws RemoteException;
    public void subscribe(int componentIDInteresse,int portSubscriber) throws RemoteException;

}
