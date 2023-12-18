package client;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class Client{
    public static void main(String[] args) {

        //Invia N messaggi sulla coda Richiesta
        Hashtable<String,String> jndiProperties = new Hashtable<>();
        jndiProperties.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		jndiProperties.put("java.naming.provider.url","tcp://127.0.0.1:61616");
        jndiProperties.put("queue.request", "requestQueue");
        jndiProperties.put("queue.reply", "replyQueue");

        try {
            Context jndiContext = new InitialContext(jndiProperties);
        
            QueueConnectionFactory QCF = (QueueConnectionFactory) jndiContext.lookup("TopicConnectionFactory");
            Queue reply = (Queue) jndiContext.lookup("reply");
            Queue request = (Queue) jndiContext.lookup("request");
            

            QueueConnection connection = (QueueConnection) QCF.createConnection();
            connection.start();
            
            Receiver receiver = new Receiver(connection,reply);
            Sender sender = new Sender(connection,request,reply);

            receiver.start();
            sender.start();
            
        
        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }




    }


}