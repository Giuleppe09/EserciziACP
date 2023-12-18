package generator;

import java.rmi.RemoteException;
import java.util.Random;

import service.AlertNotification;
import service.IManager;

public class ComponentThread extends Thread {

    private IManager stub = null;
    private String nameThread = null;

    public ComponentThread(IManager manager, String name) {
        this.stub = manager;
        this.nameThread = name;
    }

    @Override
    public void run() {
        System.out.println(nameThread+" avviato.");

        Random r = new Random();

        int id = (r.nextInt(5)+1);
        int crit = (r.nextInt(3)+1);

        System.out.println(nameThread+" creata la notifica: ["+id+","+crit+"].");
        AlertNotification notification = new AlertNotification(id, crit);

        try {
            stub.sendNotification(notification);

            System.out.println(nameThread+" inviata la notifica.");

        } catch (RemoteException e) {
            System.err.println(nameThread+" notifica non inviata");
        }

        
    
    }
    

    

}
