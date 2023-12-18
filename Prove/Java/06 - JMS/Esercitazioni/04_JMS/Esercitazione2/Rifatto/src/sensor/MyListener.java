package sensor;

import javax.jms.Message;
import javax.jms.MessageListener;

import coda.ICoda;

public class MyListener implements MessageListener{
    private ICoda coda;

    public MyListener(ICoda codaCircolare) {
        coda = codaCircolare;
    }

    @Override
    public void onMessage(Message message) {
        
        System.out.println("[Messaggio ricevuto]\n");
        TManager threadManager = new TManager(message,coda);
        threadManager.start();
    }
    
}
