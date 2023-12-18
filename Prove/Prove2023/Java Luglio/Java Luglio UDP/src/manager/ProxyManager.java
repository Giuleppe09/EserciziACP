package manager;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import service.IRider;

//Questo sempre perchè stiamo creando un solo proxy, amma vere se è giust sta cos chi sap.
public class ProxyManager implements IRider {
    private int portInvio;


    public ProxyManager(){
        portInvio = 0; //Port su cui deve avvenire l'invio
    }

    @Override
    public int notifyOrder(int idOrder, String addr) {
        int result =-1;
        try {
            DatagramSocket socket = new DatagramSocket();
        
            byte[] reply = new byte[4]; //4 byte bastano per 1 intero che sfaccimm
            String request = "notifyOrder"+"#"+idOrder+"#"+addr;
            DatagramPacket packet = new DatagramPacket(request.getBytes(), request.getBytes().length,InetAddress.getLocalHost(),portInvio);

            System.out.println("[ProxyManager] Request: "+request);
            
            socket.send(packet);
            DatagramPacket risposta = new DatagramPacket(reply, reply.length);
            socket.receive(risposta);

            String rispostaString = new String(risposta.getData(),0,risposta.getLength());
            result = Integer.parseInt(rispostaString);
            

        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return result;
    }

    public void setPortInvio(int port){
        portInvio=port;
    }
    
}
