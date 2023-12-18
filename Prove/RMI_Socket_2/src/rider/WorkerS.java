package rider;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

import service.IRider;

public class WorkerS extends Thread{
    private Socket socket;
    private IRider skel;

    public WorkerS (Socket socket, IRider skel){
        this.socket = socket;
        this.skel = skel;
    }

    /*    public void notifyOrder(int id, String address); */
    public void run(){
        try{
            DataInputStream fromClient = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
            String metodo = fromClient.readUTF();
            if(metodo.compareToIgnoreCase("notify")==0){
                int id = fromClient.readInt();
                String address = fromClient.readUTF();
                skel.notifyOrder(id, address);
                System.out.println("[WorkerS]: Upcall effettuata correttamente!\n");
            }else{
                System.out.println("[WorkerS]: Metodo ignoto, per favore invocare solo <notify>!\n");
            }
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
