package extractor;

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

public class Extractor {
    public static void main(String[] args) {
        
        System.out.println("[Extractor] Avviato correttamente per estrarre le richieste");

        //Creiamo il contesto iniziale JNDI per poter poi andare ad utilizzare JMS
        Hashtable<String,String> jndiProperties = new Hashtable<>();
        jndiProperties.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        jndiProperties.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
        jndiProperties.put("topic.data", "dataTopic");
        jndiProperties.put("topic.temp","tempTopic");
        jndiProperties.put("topic.press", "pressTopic");

        //creazione del contesto jndi da cui andremo a prelevare gli administeredObject e tramite cui andremo a connetterci con il provider ActiveMq
        try {
            Context jndiContext = new InitialContext(jndiProperties);
            System.out.println("[Client] Contesto creato correttamente.");

            //Ricaviamo gli administered objects.
            //1) Ricaviamo il TopicConnectionFactory impostato sulle proprietà con cui è inizializzato il contesto, dunque ricaviamo l'implementazione di queste classi.
            TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
            
            //2) Ricaviamo le Destination
            Topic topicData = (Topic) jndiContext.lookup("data"); //Su cui imposteremo il listener in ascolto.
            Topic topicTemp = (Topic) jndiContext.lookup("temp");
            Topic topicPress = (Topic) jndiContext.lookup("press");

            //3) Creazione della connessione
            TopicConnection topicConnection = topicConnectionFactory.createTopicConnection();
            //3.1) Abilito la connessione, per poter ricevere messaggi.
            topicConnection.start();

            //4)Creazione della session, contesto singleThreaded in cui andremo a creare un Publisher.
            TopicSession session = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

            //5)Creazione del Subscriber sottoiscrivendolo alla destination data.
            TopicSubscriber subscriber = session.createSubscriber(topicData);
            //Creazione del Listener
            ExtractorListener listener = new ExtractorListener(topicConnection, topicPress, topicTemp);
            subscriber.setMessageListener(listener);

            //Voglio pulire il to

        } catch (NamingException e) {
            System.err.println("[Client] Errore nella creazione del contesto iniziale");
        } catch (JMSException e) {
            System.err.println("[Client] Errore di natura JMS.");
        }
    
    }

}