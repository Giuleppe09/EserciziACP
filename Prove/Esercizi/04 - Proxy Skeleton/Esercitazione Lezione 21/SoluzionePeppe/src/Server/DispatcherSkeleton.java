package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import Interface.IDispatcher;

public abstract class DispatcherSkeleton implements IDispatcher {

    public void runSkeleton(){
        
        System.out.println("[DispatcherSkeleton]: In ascolto sul porto 3000!");


        try{
            
            ServerSocket server = new ServerSocket(3000);

            System.out.println("[DispatcherSkeleton]: Entro nel loop");
    
            while(true){        
        
            try {
                
                Socket sock = server.accept();
                WorkerThread workerThread = new WorkerThread(sock,this);
                workerThread.start();
                
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

     }catch(IOException e){
            System.out.println("Problema nell'apertura della server Socket");
        }

    }
}