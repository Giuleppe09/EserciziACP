package Observer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Interfacce.IObserver;
import Interfacce.IWhiteBoard;

public class ObserverClient{
    public static void main(String[] args) {
        
        try{
        Registry rmiRegistry = LocateRegistry.getRegistry();
        IWhiteBoard whiteBoard =(IWhiteBoard)rmiRegistry.lookup("whiteboard");
            
        IObserver observer = new ObserverImpl(whiteBoard);

        whiteBoard.observerAttach(observer);

        }catch(RemoteException e){
            e.printStackTrace();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}