package dispatcher;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.net.UnknownHostException;

import service.IPrinter;

public class Proxy implements IPrinter,Serializable{
    private String address;
    private int port;

    public Proxy(String ad, int p){
        this.address = ad;
        this.port = p;
    }

    public Proxy(IPrinter printer) {
        this.port = printer.getPort();
        this.address = "127.0.0.1";

    }

    public boolean print(String docName){

        boolean result = false;

        try{
            Socket socket = new Socket(address, port);
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());


            String method = "print";
            String param = docName;

            output.writeUTF(method);
            output.writeUTF(param);

            

            result = input.readBoolean();
            System.out.println("[Proxy] Stampa avvenuta con esito: "+result+"\n");
            
            
            input.close();
            output.close();
            socket.close();
        }catch(UnknownHostException uhe){
            uhe.printStackTrace();
        }catch(IOException ioe){
            ioe.printStackTrace();
        }

        return result;
    }

    @Override
    public int getPort() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getPort'");
    }
}
