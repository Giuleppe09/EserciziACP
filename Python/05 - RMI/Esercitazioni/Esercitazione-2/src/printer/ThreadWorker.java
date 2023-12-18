package printer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import service.IPrinter;

public class ThreadWorker extends Thread {
    private IPrinter skeleton;
    private Socket socket;

    public ThreadWorker(IPrinter s,Socket sock){
        super();
        skeleton=s;
        socket =sock;
    }

    public void run(){
        
       
          
            try {

                DataInputStream input = new DataInputStream(socket.getInputStream());
                DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            
                String method = input.readUTF();
                

                boolean result = false;
                
                if(method.compareTo("print")==0){
                    String docName = input.readUTF();
                    result = skeleton.print(docName);
                    System.out.println("[ThreadPrinter] Richiesta evasa con esito: "+result);
                }

                output.writeBoolean(result); //Verso il proxy.

                output.close();
                input.close();
                socket.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            

            //Unmarshalling

                       

    


    }
}
