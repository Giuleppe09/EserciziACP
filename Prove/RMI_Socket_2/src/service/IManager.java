package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

import generator.Order;

public interface IManager extends Remote {
    public void subscribe(int location, int port) throws RemoteException;
    public int setOrder(Order order) throws RemoteException;
}
