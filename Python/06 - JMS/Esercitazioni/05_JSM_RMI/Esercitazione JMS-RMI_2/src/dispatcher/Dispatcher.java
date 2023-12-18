package dispatcher;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.jms.*;

public class Dispatcher {
    public static void main(String[] args) {
        
        Hashtable<String,String> jndiProperties = new Hashtable<String,String>();
   
        jndiProperties.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        jndiProperties.put("java.naming.provider.url", "tcp://127.0.0.1:61616");

        jndiProperties.put("queue.PrinterRequest","request");

        try {
            Context jndiContext = new InitialContext(jndiProperties);
            //ottengo gli administered Object
            QueueConnectionFactory queueCF = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
            Queue queue = (Queue) jndiContext.lookup("PrinterRequest");

            //Creo la connessione
            QueueConnection connection = queueCF.createQueueConnection();
            connection.start();
            //Creo la sessione
            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            //Creo il Receiver
            QueueReceiver receiver = session.createReceiver(queue);
            //Gli assegno il Listener
            DispatcherListener listener = new DispatcherListener();
            receiver.setMessageListener(listener);
        
    
        } catch (NamingException e) {
            // TODO Auto-generated catch block
        }catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
        
    }
}
