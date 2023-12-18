package subscriberE;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import service.ISubscriber;

public abstract class SubscriberSkeleton implements ISubscriber{

    private int portSubscriber;

    public SubscriberSkeleton(int portSubscriber) {
        this.portSubscriber = portSubscriber;
    }

    public void runSkeleton(){

        try {
            
            DatagramSocket socket = new DatagramSocket(portSubscriber);
            byte[] buffer = new byte[1024];
            DatagramPacket request = new DatagramPacket(buffer, buffer.length);

            while(true){
                System.out.println("[SubscriberSkeleton] In attesa di notifiche..");
                
                socket.receive(request);
                
                String critically = new String(request.getData(),0,request.getData().length);
                this.notifyAlert(Integer.parseInt(critically));

                String ack = "ack";
                DatagramPacket ackPacket = new DatagramPacket(ack.getBytes(), ack.getBytes().length,request.getAddress(),request.getPort());
                socket.send(ackPacket);
            }   


        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
