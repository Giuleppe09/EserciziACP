package client;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;

public class Receiver extends Thread{
    private QueueConnection connection = null;
    private Queue reply = null;


    public Receiver(QueueConnection connection,Queue reply){
        this.connection = connection;
        this.reply = reply;
    }
    
    public void run(){
        try {
            QueueSession session = this.connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
        
            QueueReceiver receiver = session.createReceiver(reply);
            ReceiverListener listener = new ReceiverListener();
            receiver.setMessageListener(listener);

            System.out.println("[ReceiverListener] Avviato.");
        
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

}