package printer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import service.IPrinter;

//Per delega
public class Skeleton implements IPrinter {
    private IPrinter printer;

    public Skeleton(IPrinter p){
        this.printer = p;
    }

    public void runSkeleton(){
        try {
            ServerSocket serverSocket = new ServerSocket(printer.getPort());
            
            while(true){
                System.out.println("[SkeletonPrinters] In attesa di connessioni..");
                Socket socket = serverSocket.accept();
                System.out.println("[SkeletonPrinters] Richiesta ricevuta!");
                
                ThreadWorker worker = new ThreadWorker(this, socket);
                worker.start();
            }
        
        
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    @Override
    public boolean print(String docName) {
        return this.printer.print(docName); //UpCall
    }

    @Override
    public int getPort() {
        return printer.getPort();
    }

    

}
