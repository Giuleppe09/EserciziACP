package Observer;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import Interfacce.IObserver;
import Interfacce.IShape;
import Interfacce.IWhiteBoard;

public class ObserverImpl extends UnicastRemoteObject implements IObserver{
    private IWhiteBoard remoteWhiteboard;

    protected ObserverImpl(IWhiteBoard whiteBoard) throws RemoteException {
        super();
        remoteWhiteboard=whiteBoard; //Vedi bene.
    }

    @Override
    //CallBackMethod
    public void notifyObserver() throws RemoteException{
        System.out.println("[OBSERVER] E' stata aggiunta una forma sulla board");
    
        Vector<IShape> v = remoteWhiteboard.getShapes();
        
        for(int i=0;i<v.size();i++){
           v.get(i).draw();
        }
    }
    
}
