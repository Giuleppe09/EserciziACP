package generator;

import java.rmi.RemoteException;
import java.util.Random;

import service.IManager;
import service.Order;

public class GeneratorThread extends Thread{
    private IManager managerStub = null;

    public GeneratorThread(IManager stub){
        this.managerStub = stub;
    }

    public void run(){
        System.out.println("[GeneratorThread] Avviato");
        Random random = new Random();
        
            try {
            Order ordine = new Order(random.nextInt(100)+1,random.nextInt(5)+1,"th avenue "+(random.nextInt(6)+4));
            System.out.println("[GeneratorThread] Inviato l'odine: \nid:["+ordine.getIdOrder()+"] - location: ["+ordine.getLocation()+"] address:["+ordine.getAddress()+"]\n");
            
            int result = managerStub.setOrder(ordine);
            System.out.println("Risultato: "+result);

            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

