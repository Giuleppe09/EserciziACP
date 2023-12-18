package server_Ereditarietà;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import service.IMagazzino;

//Thread per ereditarietà
public class ThreadWorker extends Thread {
    private Socket socket = null;
    private IMagazzino m = null;

    public ThreadWorker(Socket sock, IMagazzino magazz) {
        super();
        this.socket =sock;
        this.m = magazz;
    }

    public void run(){

        try{
            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            System.out.println("[ThreadWorker] Canale di comunicazione creato correttamente");

            //Metodo invocato
            String method = input.readUTF();

            /*
             *  public void deposita(String articolo,int id);
            *   public int preleva(String articolo);
     
             */
            if(method.compareTo("deposita")==0){
                
                String articolo = input.readUTF();
                int id = input.readInt();
                m.deposita(articolo,id);
            
            }else if(method.compareTo("preleva")==0){
                
                String articolo = input.readUTF();
                int id = m.preleva(articolo);
                output.writeInt(id);
            }
            input.close();
            output.close();
            socket.close();

        }catch(IOException io){
            System.err.println("Canale non creato correttamente");
        }
    }
}