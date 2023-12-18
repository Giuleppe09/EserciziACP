package disk;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import service.ILogger;

public class DiskProxy implements ILogger {
    private int port;

    public DiskProxy(int p){
        port = p;
    }

    @Override
    public void registraDato(int dato) {
        try{
            DatagramSocket skt = new DatagramSocket();
            String stringa = "registra#"+dato;
            DatagramPacket pkt = new DatagramPacket(stringa.getBytes(), stringa.getBytes().length, InetAddress.getLocalHost(), port);
            System.out.println("[DiskProxy]: Pacchetto contenente <"+stringa+"> inviato correttamente!\n");
            skt.send(pkt);
            skt.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
