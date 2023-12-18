package Interfacce;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface IWhiteBoard extends Remote{

    public void observerAttach(IObserver observer) throws RemoteException;
    

    //Metodi per gestire la lavagna.
    public void addShape(IShape s) throws RemoteException;
    public Vector<IShape> getShapes() throws RemoteException; 

}