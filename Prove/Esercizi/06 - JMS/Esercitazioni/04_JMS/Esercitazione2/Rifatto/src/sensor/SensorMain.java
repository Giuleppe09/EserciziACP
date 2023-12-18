package sensor;

import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import coda.CodaCircolare;
import coda.CodaWrapperLock;
import coda.ICoda;

public class SensorMain {

    private static int D=5;

     public static void main(String[] args) {
        
        Hashtable<String,String> jndiProperties = new Hashtable<String,String>();
        jndiProperties.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		jndiProperties.put("java.naming.provider.url", "tcp://127.0.0.1:61616");

        jndiProperties.put("topic.Comandi","topic");
    

        try{
        
            Context jndiContext = new InitialContext(jndiProperties);

            //Lookup Administered Object - (ConnectionFactory,Destination)
            TopicConnectionFactory topicCF = (TopicConnectionFactory) jndiContext.lookup("TopicConnectionFactory");    
            Topic topic = (Topic) jndiContext.lookup("Comandi");

            //Creazione della connessione
            TopicConnection connection = (TopicConnection) topicCF.createTopicConnection();
            connection.start();
        
            //Creazione della session
            TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            
            
            //Creazione coda in cui inserire comandi
            ICoda codaCircolare = new CodaWrapperLock(new CodaCircolare(D)); 

            //Ricezione asincrona
            TopicSubscriber subscriber = session.createSubscriber(topic);
            MyListener listener = new MyListener(codaCircolare);
            subscriber.setMessageListener(listener);


            TExecutor threadExecutor = new TExecutor(codaCircolare);
            threadExecutor.start();


        }catch(NamingException ne){
            ne.printStackTrace();
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
     }
}
