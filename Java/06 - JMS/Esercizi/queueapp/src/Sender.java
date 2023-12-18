import java.util.Hashtable;
import javax.jms.*;
import javax.naming.*;

public class Sender {

    public static void main(String[] args) {
        // Hashtable per le proprietà JNDI
        Hashtable<String, String> prop = new Hashtable<String, String>();
        prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");

        // Nome JNDI della coda e il nome effettivo della coda
        prop.put("queue.test", "mytestqueue");

        try {
            // Creazione del contesto JNDI
            Context jndiContext = new InitialContext(prop);

            // Lookup degli oggetti amministrati

            // ConnectionFactory
            QueueConnectionFactory queueConnFactory = (QueueConnectionFactory) jndiContext
                    .lookup("QueueConnectionFactory");

            // Destinazione (nel contesto JNDI, il nome della coda è "test")
            Queue queue = (Queue) jndiContext.lookup("test");

            // Creazione della connessione con il broker ActiveMQ
            QueueConnection queueConn = queueConnFactory.createQueueConnection();

            // Creazione di una sessione AUTO_ACKNOWLEDGE
            QueueSession queueSession = queueConn.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            // Creazione di un produttore
            QueueSender sender = queueSession.createSender(queue);

            // Creazione di un messaggio di testo
            TextMessage message = queueSession.createTextMessage();

            // Invia 5 messaggi di testo alla coda
            for (int i = 0; i < 5; i++) {
                message.setText("hello_" + i);
                sender.send(message); // Invio del messaggio al broker che lo inserirà nella coda "test"
            }

            // Invia un messaggio di "fine"
            message.setText("fine");
            sender.send(message);

            System.out.println("I messaggi sono stati inviati!");

            // Pulizia delle risorse
            sender.close();
            queueSession.close();
            queueConn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
