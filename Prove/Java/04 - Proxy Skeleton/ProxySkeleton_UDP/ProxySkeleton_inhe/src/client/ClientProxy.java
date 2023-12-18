package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import service.Interface;

public class ClientProxy implements Interface {
    
    private DatagramSocket socket;


    //Costruttore del Proxy
    ClientProxy(){
        try { //Tenta la connessione ad una ServerSocket aperta sul port 3000 (localhost)
            socket = new DatagramSocket(3000);
        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    //Costruiamo i messaggi che andranno inviati al ServerSkeleton.
    public int increment(){
        int x =0;
        String message = new String("increment#"); //# è una convenzione stipulata per indicare la fine di un parametro
        //In questo caso il messaggio è composto solo dal metodo dato che non ha parametri il metodo increment.

        //Dato che stiamo lavorando con UDP, dobbiamo andare a creare il Datagram da inviare tramite la socket.
        try {
            DatagramPacket request = new DatagramPacket(message.getBytes(),message.getBytes().length, InetAddress.getLocalHost(), 3000);
            socket.send(request);


        //Dopo aver mandato la richiesta, adesso vogliamo semplicemente il messaggio di risposta che conterrà il valore del contatore incrementato.
        //Dunque devo definire il Datagram di response che attendiamo ricevere.

            byte[] buff = new byte[100];
            DatagramPacket response = new DatagramPacket(buff,buff.length);
            socket.receive(response);

        //Ricevuto lo stream di byte, va effettuato la conversione dello stream di bit del payload in un valore intero.
            String responseString = new String(response.getData(), 0, response.getData().length);
            x= Integer.valueOf(responseString).intValue();

            
            
        } catch (UnknownHostException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return x;
    }


}
