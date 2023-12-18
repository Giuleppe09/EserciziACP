package Rider;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import Service.IRider;

public abstract class RiderSkeleton implements IRider{
    private int portServer;
    public RiderSkeleton(int port){
        this.portServer = port;
    }
    public void runSkeleton(){
        try {
            ServerSocket sock = new ServerSocket(portServer);

            while(true){
                Socket connection = sock.accept();
                DataInputStream input = new DataInputStream(connection.getInputStream());
                DataOutputStream output = new DataOutputStream(connection.getOutputStream());

                String method = input.readUTF();


                /* output.writeUTF("notifyOrder");
            output.writeInt(id);
            output.writeUTF(address);

            result = input.readInt();
 */
                if(method.compareTo("notifyOrder")==0){
                    int id = input.readInt();
                    String address = input.readUTF();

                    int result = this.notifyOrder(id, address);
                    
                    output.writeInt(result);
                    connection.close();
                }
                
            }




        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

    }

}
