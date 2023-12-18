package client;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class ReceiverListener implements MessageListener {

    //Ricezione asincrona.
    @Override
    public void onMessage(Message message) {
        
        MapMessage reply = (MapMessage) message;

        try {
            int value = (reply.getInt("value"));
            System.out.println("[Listener] Valore prelevato:"+value);

        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        
    }
    
}
