package Server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import Interface.IDispatcher;

public class WorkerThread extends Thread{
    private Socket sock = null;
    private IDispatcher skeleton = null;
    
    private DataInputStream in=null;
    private DataOutputStream out=null;
    
    //Costruttore
    public WorkerThread(Socket s, IDispatcher d){
        super();

        sock = s;
        skeleton = d; //Sarebbe l'elemento con cui posso poi andare a fare l'upcall

        try {
            in = new DataInputStream(sock.getInputStream());
            out = new DataOutputStream(sock.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public void run(){
        //Riconosciamo il metodo invocato e l'eventuale parametro
        
        //Il primo messaggio della connessione è sempre il nome del metodo
        //poi a seconda se è una send o una get avremo o meno un parametro (cmd)

        String method;
        
        String response = new String("ACK");

        try{

            method = in.readUTF();

            if(method.equals("sendCmd")){

                int comando = in.readInt();
                skeleton.sendCmd(comando);
                
                System.out.println("[WorkerThread]: comando scritto nella coda.");
                out.writeUTF(response);
                
                
            }else{ //significa che il metodo letto è getCmd
                
                skeleton.getCmd();
                System.out.println("[WorkerThread]: comando prelevato dalla coda");
                
                out.writeUTF(response);
            }


        } catch (IOException e) {
        // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
