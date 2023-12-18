package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import Interface.IDispatcher;

public class Proxy implements IDispatcher{
    //Realizza la comunicazione tramite protocollo TCP verso il Server
    private Socket sock =null;
    private DataOutputStream out = null; //lo utilizzeremo per la sendCmd
    private DataInputStream in = null; //lo utilizzeremo per la getCmd

    public Proxy(){
        try {
      
            //Tenta la connessione sulla socket Server aperta sul port 3000
            sock = new Socket("127.0.0.1",3000); //Questo è un metodo bloccante,
            //Dunque il thread corrente si blocca finchè non viene stabilita una connessione con il Server.

            
            out = new DataOutputStream(sock.getOutputStream());
            in = new DataInputStream(sock.getInputStream());

        } catch(UnknownHostException e){
            System.err.println("Host non riconosciuto!");
        } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
        }
    }



    public void sendCmd(int command){
        //Va fatto il marshalling dei parametri
        //Dato che stiamo utilizzando TCP posso anche mandare tramite 2 messaggi separati, senza fare con stringTokenizer

        String method = new String("sendCmd");

        try {
            out.writeUTF(method);
            out.writeInt(command);
    
            System.out.println("[Proxy]: Comando inviato, in attesa di un messaggio di ACK!");
            //Ricezione del messaggio di Ack
        
            String res = in.readUTF();
            
            if(res.equals("ACK")){
                System.out.println("ACK Ricevuto!");
            }else{
                System.err.println("Non ricevuto l'ACK");
            }

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public int getCmd(){
        String method = new String("getCmd"); //Creo il messaggio
        int cmd=-1;
        
        try {
            out.writeUTF(method); //Invio il messaggio
            System.out.println("[Proxy]: Richiesta get inviata");


            //Leggo il comando in input.
            cmd = in.readInt();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return  cmd;
    }
}

