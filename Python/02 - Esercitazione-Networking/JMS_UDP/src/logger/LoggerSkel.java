package logger;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import service.ILogger;

public abstract class LoggerSkel implements ILogger {
    private int port;

    public LoggerSkel(int port){
        this.port = port;
    }

    public void runSkeleton(){
        try{
            DatagramSocket skt = new DatagramSocket(port);
            while(true){
                byte[] data = new byte[1024];
                DatagramPacket pkt = new DatagramPacket(data, data.length);
                skt.receive(pkt);
                WorkerS worker = new WorkerS(pkt,this);
                worker.start();
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
