package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import service.IMagazzino;

//Proxy con TCP

public class ProxyTCP implements IMagazzino{
   // private Socket socket; non ha senso perchè altrimenti avremmo un unico punto of failure

    int portNumber; //portNumber dove il Server sarà in ascolto.

    public ProxyTCP(String port){
        port = port.trim();
        this.portNumber=Integer.parseInt(port);
    
    }


    @Override
    public void deposita(String articolo, int id) {
        try{
            Socket socket = new Socket("127.0.0.1",portNumber);
            DataInputStream input = new DataInputStream(socket.getInputStream()); //Realizziamo l'ACK esplicito.
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());


            String method = "deposita";
            String articoloInviare= articolo;
            int idInviare = id;

            output.writeUTF(method);
            output.writeUTF(articoloInviare);
            output.writeInt(idInviare);

            System.out.println("[ProxyTCP]"+method+"("+articoloInviare+","+idInviare+") inviato al Magazzino.");

            System.out.println("[ProxyTCP]In attesa dell'Ack..");
            String ack = input.readUTF();
            System.out.println("[ProxyTCP] "+ack+"\n");

            socket.close();
        }catch(IOException e){
            System.out.println("Errore nella creazione del canale Bidirezionale.");
        }

    }
       

    @Override
    public int preleva(String articolo) {
        int id_articolo=-1; 

        try{
            Socket socket = new Socket("127.0.0.1",portNumber);
            DataInputStream input = new DataInputStream(socket.getInputStream()); //Realizziamo l'ACK esplicito.
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());


            String method = "preleva";
            String articoloDaPrelevare= articolo;
        

            output.writeUTF(method);
            output.writeUTF(articoloDaPrelevare);

            System.out.println("[ProxyTCP]"+method+"("+articoloDaPrelevare+") inviato al Magazzino.");

            System.out.println("[ProxyTCP] In attesa dell'id..");
            id_articolo = input.readInt();
            System.out.println("[ProxyTCP] "+id_articolo+"\n");


            socket.close();

        }catch(IOException e){
            System.out.println("Errore nella creazione del canale Bidirezionale.");
        }

        return id_articolo;
    }
    
        

}
