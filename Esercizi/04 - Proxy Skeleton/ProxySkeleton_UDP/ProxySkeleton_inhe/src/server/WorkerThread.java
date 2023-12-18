package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.StringTokenizer;

import service.Interface;

public class WorkerThread extends Thread {

    private DatagramSocket socket=null;
    private DatagramPacket req=null;
    private Interface serv = null;

    WorkerThread(DatagramSocket s, DatagramPacket request, Interface impl){
        socket = s;
        req=request;
        serv=impl;
    }

    public void run(){
        //unmarshalling del messaggio
        String message = new String(req.getData(),0,req.getData().length);

        System.out.println("\n[WorkerThread] Processing Packet -> message = " + message);

        StringTokenizer messaggeTokens = new StringTokenizer(message,"#");
        String method = messaggeTokens.nextToken();

        if(method == "increment"){
            byte[] buff= new byte[100];
            int app;
           

            app=serv.increment();
            String replyMessage=Integer.toString(app);
            
            DatagramPacket reply = new DatagramPacket(replyMessage.getBytes(), buff.length, req.getAddress(),req.getPort());
        
            
            try {
                socket.send(reply);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
        
        }


    }
    
}