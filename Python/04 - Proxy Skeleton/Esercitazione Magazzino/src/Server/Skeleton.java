package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Service.IMagazzino;

public abstract class Skeleton implements IMagazzino{
 
    private ServerSocket serverSocket=null;
    private Socket socket =null;


    public void runSkeleton(){

        try{
            serverSocket = new ServerSocket(3000);

            System.out.println("[Server Skeleton]: Server in ascolto sul port 3000!");

            while(true){
            
            socket = serverSocket.accept(); //Accettiamo una connessione in arrivo, ricorda che è un metodo bloccante.
            //Assegnamo una determinata connessione ad un Thread Worker Server

            ThreadWorkerServer thr = new ThreadWorkerServer(socket,this);
            thr.start();
            }
        
        }catch(IOException e){
            System.err.println("Errore nelle Socket");
        }
    
    }

}
