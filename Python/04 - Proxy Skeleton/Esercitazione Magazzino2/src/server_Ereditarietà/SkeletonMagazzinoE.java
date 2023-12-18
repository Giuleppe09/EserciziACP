package server_Ereditarietà;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import service.IMagazzino;

//Prima per Ereditarietà e poi per delega
public abstract class SkeletonMagazzinoE implements IMagazzino{

    public void runSkeleton(){

        try {
            
            ServerSocket serverSocket = new ServerSocket(3000);
           
            while(true){
                
                System.out.println("[Skeleton] In attesa di connessioni..\n");
                Socket sock = serverSocket.accept();

                System.out.println("[Skeleton] Connessione rilevata, attivo un Thread Worker..\n");
                ThreadWorker worker =new ThreadWorker(sock,this); //passiamo this dato che nel main andremo a istanziare MagazzinoImpl
                worker.start();
            
            }

        } catch (IOException e) {
            System.err.println("Errore nell'apertura della ServerSocket");
        }

    
    }
}
