package server_Delega;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import server_Ereditarietà.ThreadWorker;
import service.IMagazzino;

public class SkeletonMagazzinoD implements IMagazzino{

    private IMagazzino mag;

    public SkeletonMagazzinoD(IMagazzino mag){
        this.mag = mag;
    }


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



    @Override
    public void deposita(String articolo, int id) {
        mag.deposita(articolo, id);
    }

    @Override
    public int preleva(String articolo) {
       return mag.preleva(articolo);
    }
     
}