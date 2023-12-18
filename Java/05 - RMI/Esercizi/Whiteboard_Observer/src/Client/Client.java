package Client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import Interfacce.IWhiteBoard;
import Interfacce.IShape;

import Shape.Triangle;
import Shape.Square;

public class Client {
    public static void main(String[] args) {
        
        try{
        
            Registry rmiRegistry = LocateRegistry.getRegistry();
            IWhiteBoard whiteBoard = (IWhiteBoard)rmiRegistry.lookup("whiteboard");
        
            IShape s;

            for(int i=0;i<4;i++){
                if(i%2==0){
                    s= new Triangle();
                }else{
                    s= new Square();
                }

                whiteBoard.addShape(s);
               
                Thread.sleep(5000);
            }
            
        
        }catch(RemoteException e){
            e.printStackTrace();
        } catch (NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }
    
}
