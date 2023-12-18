package disk;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import service.ILogger;

public class Proxy implements ILogger{

    private int portServer;

    public Proxy(int port){
        this.portServer = port;
    }

    @Override
    public void registraDato(int dato) {
        String method = "registraDato#"+dato+"#";
        System.out.println("[Proxy] Invio il messaggio: "+method);

        try {
            Socket socket = new Socket("127.0.0.1",portServer);

            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            
           
            output.writeUTF(method);

            System.out.println("[Proxy] Messaggio inviato.");

            socket.close();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
}
