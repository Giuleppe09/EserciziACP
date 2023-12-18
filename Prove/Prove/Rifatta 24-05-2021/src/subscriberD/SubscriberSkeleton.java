package subscriberD;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import service.ISubscriber;

public class SubscriberSkeleton implements ISubscriber{
    private ISubscriber subscriber;
    private int portSubscriber;

    public SubscriberSkeleton(ISubscriber subImpl,int portSubscriber) {
        this.subscriber = subImpl;
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
                String trimmata = critically.trim();
                int crit = Integer.parseInt(trimmata);
                this.subscriber.notifyAlert(crit);

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

    @Override
    public void notifyAlert(int critically) {
        this.subscriber.notifyAlert(critically); //UpCall.
    }
}
