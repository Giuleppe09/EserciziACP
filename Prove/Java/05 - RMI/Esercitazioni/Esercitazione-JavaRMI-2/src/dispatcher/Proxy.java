package dispatcher;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import service.IPrinter;

public class Proxy implements IPrinter{

    Socket socket = null;
    int port;

    public Proxy(int p) {
        this.port=p;
    }

    @Override
    public boolean print(String docName) {

        try{
            socket = new Socket("127.0.0.1",port);
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF("print");
            out.writeUTF(docName);
            out.flush();

            boolean result = in.readBoolean();
            socket.close();

            return result;

        }catch(IOException e){

            System.out.println("[PROXY] Errore nello stabilire la connessione!");
        }
        return false;
    }
    
}
