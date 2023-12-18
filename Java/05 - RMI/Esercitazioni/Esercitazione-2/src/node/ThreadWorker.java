package node;

import java.rmi.RemoteException;
import java.util.Random;

import service.IDispatcher;

public class ThreadWorker extends Thread {
    IDispatcher dispatcher=null;

    public ThreadWorker(IDispatcher disp){
        super();
        this.dispatcher=disp;
    }

    public void run(){
        Random random = new Random();
        for(int i=0;i<3;i++){
            String docName = "docName"+(random.nextInt(50)+1);
            try {
                System.out.println("[ThreadWorker] Invio richiesta di stampa : "+docName+"/n");
                boolean result= dispatcher.printRequest(docName);
                
                System.out.println("[ThreadWorker] Richiesta di stampa di "+docName+" esito: "+result);
            } catch (RemoteException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
