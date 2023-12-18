package disk;

import javax.jms.*;

public class DiskThread extends Thread{
    private MapMessage mm;

    public DiskThread(MapMessage mm){
        this.mm = mm;
    }

    public void run(){
        try{
            int dato = mm.getInt("dato");
            int port = mm.getInt("port");
            System.out.println("[DiskThread]: Il messaggio ricevuto contiene la coppia (dato, port) = ("+dato+", "+port+")!\n");
            System.out.println("[DiskThread]: Proxy avviato correttamente!\n");
            DiskProxy proxy = new DiskProxy(port);
            proxy.registraDato(dato);
        }catch(JMSException e){
            e.printStackTrace();
        }
    }
}
