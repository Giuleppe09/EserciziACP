package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;



import service.Interface;

//Invocheremo più thread perchè stiamo considerando un Server UDP multithreaded

public abstract class ServerSkeleton implements Interface { 
    //Lo poniamo in ascolto sulla porta 3000, su cui arriveranno i messaggi del ProxyClient
    DatagramSocket socket = null;

    public void runSkeleton(){
        //Definiamo il datagram di request che lo Skeleton si aspetta.
        byte[] buff = new byte[100];
        DatagramPacket request = new DatagramPacket(buff,buff.length);
       
        

        while(true){
            try {
            
                socket = new DatagramSocket(3000);
                socket.receive(request);
            
            //istanziamo dunque un WorkerThread che prende in affidamento questa richiesta ed effettui l'unmarshalling
            //ed elabori la richiesta, eventualmente mandando anche la risposta.

                //Passiamo this che sarebbe in realtà un'istanza di ServerImpl che,estendendo Skeleton, implementa l'interfaccia Interface.
                WorkerThread thr = new WorkerThread(socket, request, this);
                thr.start();
            
                socket.close();


        } catch (SocketException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        }

    }

}
