package rider;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import service.IRider;

public class RiderSkeleton implements IRider{
    private int port;
    private RiderImpl rider;

    public RiderSkeleton(int p, RiderImpl r){
        port = p;
        rider = r;
    }

    public void runSkeleton(){
        ServerSocket server = null;
        Socket socket = null;
        try{
            server = new ServerSocket(port);
            System.out.println("[RiderSkeleton]: Server in ascolto sul porto "+port+"!\n");
            while(true){
                socket = server.accept();
                WorkerS worker = new WorkerS(socket, this);
                worker.start();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void notifyOrder(int id, String address) {
        rider.notifyOrder(id, address);
    }
    
}
