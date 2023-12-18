package manager;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import service.ISubscriber;

public class Proxy implements ISubscriber{
    private int portSubscriber;

    public Proxy(int port){
        this.portSubscriber = port;
    }

    @Override
    public void notifyAlert(int critically) {
        //Facciamo con Proxy-Skeleton per delega UDP.
        try {
            DatagramSocket socket = new DatagramSocket(); //non inseriamo il port, sarà il port del proxy.
            
            String message = Integer.toString(critically);

            DatagramPacket packet = new DatagramPacket(message.getBytes(), message.getBytes().length,InetAddress.getLocalHost(),this.portSubscriber);
            socket.send(packet);

            System.out.println("[Proxy] Ho inviato la notifica al port: "+portSubscriber);

            byte[] ack = new byte[1024];
            DatagramPacket reply = new DatagramPacket(ack,ack.length);
            socket.receive(reply);

            String risposta = new String(reply.getData(),0,reply.getData().length);
            System.out.println("[Proxy]"+risposta+" ricevuto.");

            socket.close();



        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void setPortSubscriber(int p){
        this.portSubscriber = p;
    }
}
