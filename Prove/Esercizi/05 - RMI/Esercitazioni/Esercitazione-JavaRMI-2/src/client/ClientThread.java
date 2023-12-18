package client;

import java.rmi.RemoteException;
import java.util.Random;

import service.IDispatcher;

public class ClientThread extends Thread{
    private IDispatcher disp; 

    
    public ClientThread(IDispatcher d){ //Ai ClientThread passiamo lo stub che contiene i metodi remoti offerti dal Dispatcher.
        this.disp=d;
    }


    public void run(){
        Random rand=new Random();
        String docName;
        
        try {
        
            for(int i=0;i<3;i++){
                Thread.sleep(3000); //Ogni 3 secondi invoco una printRequest
                docName = "docName#"+rand.nextInt(51);

                System.out.println("[ThreadClient] Richiesta di stampa del documento ["+docName+"] inviato correttamente al dispatcher...");
                boolean result=disp.printRequest(docName);
                System.out.println("[ThreadClient]: Print request " + docName + " had result " + result);
            }

        } catch (RemoteException e) {
                // TODO Auto-generated catch block
                System.out.println("[ThreadClient] Problema nell'invio tramite RMI della printRequest!");
        }catch(InterruptedException e){
                e.printStackTrace();
        }
    }
}
