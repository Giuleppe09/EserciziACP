package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.StringTokenizer;

import Service.IMagazzino;

public class ThreadWorkerServer extends Thread {

    private Socket socket = null;
    private IMagazzino magazz = null; //Che poi in realtà sarà = ad un MagazzinoImpl

    public ThreadWorkerServer(Socket s, IMagazzino m){
        socket = s;
        magazz = m;
    }

    @Override
    public void run(){

        
        try {
        
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

          
            String request = in.readUTF();
            
            StringTokenizer messageTokens= new StringTokenizer(request,"#");
            
            String method = messageTokens.nextToken();
            String type = messageTokens.nextToken();
            
           
            
            if(method.compareTo("Deposita")==0){
                
                String id = messageTokens.nextToken();
                System.out.println("[SERVER] Deposita("+type+","+id+")");
                magazz.Deposita(type, Integer.parseInt(id));

            }else{//foss preleva 
              int idReturn = 0; 
              idReturn = magazz.Preleva(type);
            
                System.out.println("[SERVER] Preleva("+type+")");
              out.writeInt(idReturn);
            
                
            }




        }catch (IOException e) {

            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            try {
                socket.close();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }

}
