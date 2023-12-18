package printer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import service.IPrinter;

public class Skeleton implements IPrinter {

    private ServerSocket serverSocket;
    private IPrinter printer;
    private int port;


    public Skeleton(IPrinter pr, int p ){ //Gli passiamo un oggetto IP
        this.printer =  pr;
        this.port = p;
       
    }

    public void runSkeleton(){

        try {
            serverSocket = new ServerSocket(port);

            while(true){
                
                Socket sock = serverSocket.accept();
            
                PrinterThread prThr = new PrinterThread(sock,printer);
                prThr.start();

            }



        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    @Override
    public boolean print(String docName) {//Metodo che in realtà non verrà mai chiamato!

        printer.print(docName);
        return false;
    }

     
}