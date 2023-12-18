package client;

import java.util.Hashtable;

import javax.naming.InitialContext;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.NamingException;

public class Client{
    public static void main(String[] args) {
        
        if(args.length!=2){
            System.err.println("[Main] Errore.");
            return;
        }   

        int dato = Integer.parseInt(args[0]);
        int portLoggerServer = Integer.parseInt(args[1]);
        
        System.out.println("[Main] Creerò il MapMessage: ["+dato+","+portLoggerServer+"]");

        //Andiamo a creare l'hashtable che conterrà le informazioni per inizializzare il contesto JNDI.
        Hashtable<String,String> jndiProperties = new Hashtable<>();
        jndiProperties.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        jndiProperties.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
        jndiProperties.put("topic.topicone", "myTopic");

        try {
            Context jdniContext = new InitialContext(jndiProperties);
            System.out.println("[Main] Contesto creato correttamente.");

            System.out.println("[Main] Otteniamo gli administered objects.");
            TopicConnectionFactory topicCF = (TopicConnectionFactory) jdniContext.lookup("TopicConnectionFactory");
            Topic topic = (Topic) jdniContext.lookup("topicone");

            System.out.println("[Main] Creazione della connection, contattando il provider ActiveMQ.");
            TopicConnection connection = (TopicConnection) topicCF.createConnection();

            System.out.println("[Main] Creazione della sessione, in cui andremo a istanziare un publisher.");
            TopicSession session = (TopicSession) connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            
            System.out.println("[Main] Creazione del MessageSender.");
            TopicPublisher publisher = session.createPublisher(topic);

            System.out.println("[Main] Creazione del MapMessage.");
            MapMessage mex = session.createMapMessage();
            mex.setInt("port", portLoggerServer);
            mex.setInt("dato", dato);

            System.out.println("[Main] Publish.");
            publisher.publish(mex);
            

            System.out.println("[Main] Deallocazione delle risorse.");
            publisher.close();
            session.close();
            connection.close();


        } catch (NamingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}