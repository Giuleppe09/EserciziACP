package Service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface IManager extends Remote {

    public int setOrder(Order ordine) throws RemoteException;
    public void subscribe(int location,int port) throws RemoteException;
}
