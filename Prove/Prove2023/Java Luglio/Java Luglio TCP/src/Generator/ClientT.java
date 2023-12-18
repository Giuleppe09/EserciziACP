package Generator;

import java.rmi.RemoteException;
import java.util.Random;

import Service.IManager;
import Service.Order;

public class ClientT extends Thread{
    private IManager stub = null;
    public ClientT(IManager m){
        stub = m;
    }
    public void run(){
        //Ciascun thread invoca setOrder
        Random r = new Random();
        int id = r.nextInt(100)+1;
        int location = r.nextInt(5)+1;
        String address = "th-avenue"+(r.nextInt(7)+4);

        Order ordine = new Order(id, location, address);
        System.out.println("[CT] Inviato l'ordine: ["+id+","+location+","+address+"].");
        try {
            stub.setOrder(ordine);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
