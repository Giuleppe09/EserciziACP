package server_Delega;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import service.IMagazzino;

//thread per delega.
public class Worker implements Runnable{
    
    private Socket socket=null;
    private IMagazzino magazzino=null;
    
    public Worker(Socket s,IMagazzino m){
        this.socket = s;
        this.magazzino=m;
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
                magazzino.deposita(articolo,id);
            
            }else if(method.compareTo("preleva")==0){
                
                String articolo = input.readUTF();
                int id = magazzino.preleva(articolo);
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
