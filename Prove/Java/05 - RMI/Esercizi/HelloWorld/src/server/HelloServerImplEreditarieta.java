package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import service.Hello;

// Implementazione per delega

public class HelloServerImplEreditarieta extends UnicastRemoteObject implements Hello{

    protected HelloServerImplEreditarieta() throws RemoteException {
        super(); //Viene chiamato il costruttore della classe UnicastRemoteObject
    }

    public String sayHello() {
        System.out.println("sayHello() invoked on server...");
        return "Hello, world!";
    }
    
}
