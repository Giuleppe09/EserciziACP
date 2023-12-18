package client;

import java.rmi.RemoteException;
import java.util.Random;

import service.IGestoreSportelli;

public class ThreadClient extends Thread {
    private int numRequest;
    IGestoreSportelli gestore = null;

    public ThreadClient(int r, IGestoreSportelli stubGestoreSportello) {
        super();
        numRequest =r ;
        gestore = stubGestoreSportello;
    }

    @Override
    public void run(){
        Random random = new Random();

        for(int i=0;i<numRequest;i++){
            try {

                Thread.sleep((random.nextInt(3)+1)*1000);
                boolean request = gestore.sottoponiRichiesta(random.nextInt(100)+1);
                System.out.println("[Thread] Esito Richiesta: "+request);

            }catch(InterruptedException ie){
                
                ie.printStackTrace();
            
            }catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }


    
}
