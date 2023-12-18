package disk;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;

public class DiskThread  extends Thread{
    
    private Message mex = null;

    public DiskThread(Message mex){
        this.mex = mex;
    }


    public void run(){
        //UnMarshalling..
        MapMessage messaggio = (MapMessage)mex;
        try {
            int dato = messaggio.getInt("dato");
            int portLogger = messaggio.getInt("port");

            System.out.println("[DiskThread] Messaggio ricevuto: "+dato+","+portLogger);

            Proxy proxy = new Proxy(portLogger);
            proxy.registraDato(dato);

        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
         

    }
}
