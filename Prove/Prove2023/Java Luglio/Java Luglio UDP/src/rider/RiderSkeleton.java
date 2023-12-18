
//Skeleton udp

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.StringTokenizer;

import service.IRider;

public class RiderSkeleton implements IRider{
    private IRider impl;
    private int port;
    
    public RiderSkeleton(int port,IRider implementazione){
        this.port = port;
        this.impl = implementazione;
    }
    public void runSkeleton(){
        System.out.println("[Skeleton] Avviato correttamente.");
        try {
        
            DatagramSocket socket = new DatagramSocket(port);
            byte[] request = new byte[1024];

            DatagramPacket packetRequest = new DatagramPacket(request,request.length);

            while(true){
                System.out.println("[Skeleton] In attesa di messaggi di richiesta.");
                socket.receive(packetRequest);
                String req = new String(packetRequest.getData(),0,packetRequest.getData().length);
                System.out.println("[Skeleton] Richiesta arrivata: "+req);

                StringTokenizer stringTokenizer = new StringTokenizer(req, "#");
                String method = stringTokenizer.nextToken();

                if(method.compareTo("notifyOrder")==0){
                    int idOrder = Integer.parseInt(stringTokenizer.nextToken());
                    String address = stringTokenizer.nextToken();

                    int resultValue = this.impl.notifyOrder(idOrder, address);
                    String result = Integer.toString(resultValue);

                    DatagramPacket packetReply = new DatagramPacket(result.getBytes(), result.getBytes().length,packetRequest.getAddress(),packetRequest.getPort());
                    socket.send(packetReply);

                }else{
                    //Metodo non riconosciuto
                    System.out.println("[Skeleton] Metodo non riconosciuto");
                }
                
                
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
    public int notifyOrder(int idOrder, String addr) {
      return this.impl.notifyOrder(idOrder, addr);
    }
    
}
