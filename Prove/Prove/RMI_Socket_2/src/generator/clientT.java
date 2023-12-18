package generator;

import java.rmi.RemoteException;
import java.util.Random;

import service.IManager;


public class clientT extends Thread {
    private IManager stub;

    public clientT(IManager stub){
        this.stub = stub;
    }

    public void run(){
        try{
            Random r = new Random();
            int id = r.nextInt(100)+1;
            int location = r.nextInt(5)+1;
            String address = (r.nextInt(7)+4)+"th avenue";
            Order order = new Order(id, location, address);
            stub.setOrder(order);
            System.out.println("[clientT]: <setOrder> invocato correttamente con ordine -> ("+order.getId()+" - "+order.getLocation()+" - "+order.getAddress()+")!\n");
        }catch(RemoteException e){
            e.printStackTrace();
        }
    }
}
