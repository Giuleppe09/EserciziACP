package manager;

import java.io.BufferedOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import service.IRider;

public class RiderProxy implements IRider{
    private int location;
    private String host;
    private int port;

    public RiderProxy(int p, int l){
        location = l;
        host = "127.0.0.1";
        port = p;
    }

    @Override
    public void notifyOrder(int id, String address) {
        try{
            Socket socket = new Socket(host,port);
            DataOutputStream toServer = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            toServer.writeUTF("notify");
            toServer.writeInt(id);
            toServer.writeUTF(address);
            toServer.flush();
            socket.close();
            System.out.println("[RiderProxy]: Comunicazione avviata correttamente!\n");
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public int getLocation(){
        return location;
    }
}