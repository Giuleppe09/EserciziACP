package logger;

import java.net.DatagramPacket;
import java.util.StringTokenizer;

public class WorkerS extends Thread{
    private DatagramPacket pkt;
    private LoggerSkel skel;

    public WorkerS(DatagramPacket pkt, LoggerSkel skel) {
        this.pkt = pkt;
        this.skel = skel;
    }


    public void run(){
        String stringa = new String(pkt.getData(), 0, pkt.getLength());
        StringTokenizer tokens = new StringTokenizer(stringa, "#");
        String metodo = tokens.nextToken();
        if(metodo.compareToIgnoreCase("registra")==0){
            int dato = Integer.parseInt(tokens.nextToken());
            skel.registraDato(dato);
            System.out.println("[WorkerS]: Invocato correttamente <registraDato> sullo skeleton!\n");
        }      
    }
}
