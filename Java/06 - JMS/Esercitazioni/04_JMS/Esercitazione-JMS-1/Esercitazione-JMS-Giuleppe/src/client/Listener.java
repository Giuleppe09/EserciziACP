package client;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class Listener implements MessageListener{

    @Override
    public void onMessage(Message message) {
        System.out.println("[ListenerClient] Messaggio arrivato dalla coda Reply.\n");
        TextMessage m = (TextMessage) message;
    
       try {

        System.out.println("Articolo Prelevato: "+m.getText());
    
    } catch (JMSException e) {
        System.err.println("Errore nella ricezione del messaggio dalla queue");
    }
    }
    
}
