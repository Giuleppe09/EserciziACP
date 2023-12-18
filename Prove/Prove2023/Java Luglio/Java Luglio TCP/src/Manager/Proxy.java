package Manager;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import Service.IRider;

public class Proxy implements IRider{

    private int port;

    public Proxy(int p){
        this.port = p;
    }

    @Override
    public int notifyOrder(int id, String address) {
        int result = -1;
        try {
            Socket sock = new Socket("127.0.0.1",port);
            DataOutputStream output = new DataOutputStream(sock.getOutputStream());
            DataInputStream input = new DataInputStream(sock.getInputStream());

            output.writeUTF("notifyOrder");
            output.writeInt(id);
            output.writeUTF(address);

            result = input.readInt();

            sock.close();

        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
    
    public void setPort(int p){
        this.port = p;
    }
}
