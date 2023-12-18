package service;

import java.rmi.Remote;
import java.rmi.RemoteException;


//Sarà l'interfaccia che implementerà il Dispatcher e che richiederà il Client, nel contesto RMI.
//Probabilmente verrà implementata anche dalle stampanti dato che dovranno poter invocare il metodo addPrinter()

public interface IDispatcher extends Remote{ //Estende remote dato che deve esporre al client tramite RMI il metodo printRequest.

    public void addPrinter(int port) throws RemoteException; //verrà invocato dalle stampanti - sfrutta Proxy-Skeleton/TCP
    
    public boolean printRequest(String docName) throws RemoteException; //verrà invocato dai Client - sfrutta RMI  
    //Dato che sfrutta RMI, può causare un'exception -> RemoteException
    
} 