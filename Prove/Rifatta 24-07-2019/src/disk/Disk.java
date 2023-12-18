package disk;

import java.util.Hashtable;

import javax.naming.InitialContext;
import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicSession;
import javax.jms.TopicSubscriber;
import javax.naming.Context;
import javax.naming.NamingException;



public class Disk{
    public static void main(String[] args) {
    
        //Andiamo a creare l'hashtable che conterrà le informazioni per inizializzare il contesto JNDI.
        Hashtable<String,String> jndiProperties = new Hashtable<>();
        jndiProperties.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        jndiProperties.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
        jndiProperties.put("topic.topicone", "myTopic");

        try {
            Context jdniContext = new InitialContext(jndiProperties);
            System.out.println("[Disk] Contesto creato correttamente.");

            System.out.println("[Disk] Otteniamo gli administered objects.");
            TopicConnectionFactory topicCF = (TopicConnectionFactory) jdniContext.lookup("TopicConnectionFactory");
            Topic topic = (Topic) jdniContext.lookup("topicone");

            System.out.println("[Disk] Creazione della connection, contattando il provider ActiveMQ.");
            TopicConnection connection = (TopicConnection) topicCF.createConnection();
            connection.start();

            System.out.println("[Disk] Creazione della sessione, in cui andremo a istanziare un publisher.");
            TopicSession session = (TopicSession) connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
            System.out.println("[Disk] Creazione del Subscriber, e del relativo listener.");
            TopicSubscriber subscriber = session.createSubscriber(topic);
            DiskListener listener = new DiskListener();
            subscriber.setMessageListener(listener);


        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}