package logger;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.StringTokenizer;

import service.ILogger;

public abstract class LoggerSkeleton implements ILogger {
    
    private int portServer;

    public LoggerSkeleton(int port){
        this.portServer = port;
    }

    public void runSkeleton(){

        try {
            ServerSocket s = new ServerSocket(portServer);

            while(true){

                System.out.println("[Skeleton] In attesa di connessioni..");
                
                Socket connection = s.accept();
                System.out.println("[Skeleton] Tentativo di connessione.. accettato.");
                DataInputStream input = new DataInputStream(connection.getInputStream());
                String request = input.readUTF();
                
                StringTokenizer tokenizer = new StringTokenizer(request,"#");
                String method = tokenizer.nextToken();
                
                if(method.compareTo("registraDato")==0){
                    String dato = tokenizer.nextToken();

                    System.out.println("[Skeleton] E' arrivata una richiesta di registrazione del dato: "+dato);

                    //LoggerImpl sarà acceduto in mutua esclusione.
                    int intero= Integer.parseInt(dato);
                    registraDato(intero);

                }else{
                    System.out.println("[Skeleton] Metodo non presente.");
                }

            }


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }

}
