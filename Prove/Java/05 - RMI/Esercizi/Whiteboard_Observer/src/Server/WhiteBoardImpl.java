package Server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import Interfacce.*;

public class WhiteBoardImpl extends UnicastRemoteObject implements IWhiteBoard {

    private Vector<IObserver> observers;
    private Vector<IShape> contenutoBoard;

    public WhiteBoardImpl() throws RemoteException{
        super();
        observers = new Vector<IObserver>();
        contenutoBoard = new Vector<IShape>();
    }


    public void observerAttach(IObserver observer){
        observers.add(observer);
        System.out.println("[WHITEBOARD] Observer Attached.");
    }

    private void notifyAllObserver(){
        for(int i=0;i<observers.size();i++){
           
            try{
                observers.get(i).notifyObserver();
            }catch(RemoteException e){
                e.printStackTrace();
            }
        }
    }


    @Override
    public void addShape(IShape s) { 
    //Bisogna gestire la mutua esclusione su questo metodo
        
        System.out.println("\n Aggiungi la forma: #"+s.toString());
        s.draw();
        contenutoBoard.add(s);

        notifyAllObserver();
        
    }


    @Override
    public Vector<IShape> getShapes() {
        return contenutoBoard;
    }

    
}
