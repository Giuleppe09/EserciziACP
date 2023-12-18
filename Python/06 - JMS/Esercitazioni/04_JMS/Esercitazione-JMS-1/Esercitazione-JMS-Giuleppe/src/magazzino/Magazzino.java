package magazzino;

import java.util.Hashtable;
import javax.naming.*;
import coda.CodaCircolare;
import coda.CodaWrapperLock;
import coda.ICoda;

import javax.jms.*;


public class Magazzino {
    
    protected ICoda codaCircolare=null;
    
    public Magazzino(int size){
        codaCircolare = new CodaWrapperLock(new CodaCircolare(5));
    }
    

    public static void main(String[] args) {
        //Configuriamo la tabella delle properties per JNDI.
        Hashtable<String,String> jndiProperties = new Hashtable<String,String>();
        jndiProperties.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory"); //Dove andare a prendere gli oggetti per creare il contesto iniziale.
        jndiProperties.put("java.naming.provider.url", "tcp://127.0.0.1:61616"); //Dove contattare il provider JMS, ActiveMQ sul port 61616.
        //P2P
        jndiProperties.put("queue.Request","request");
        
        try{
            //Configuro il contesto JNDI per poter lavorare con il provider ActiveMQ
            Context jndiContext = new InitialContext(jndiProperties);
            //Ottengo gli administered Object.

            //1) ConnectionFactory
            QueueConnectionFactory queueCF = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
            
            //2) Ottengo le Destination.
            Queue request = (Queue) jndiContext.lookup("Request");

            //3)Connection
            QueueConnection connection = (QueueConnection) queueCF.createQueueConnection();

            //3.1) avvio la connessione per permettere la ricezione dei messaggi di risposta.
            connection.start();

            //4)Sessione
            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            
            //Il magazzino mantiene una coda, che si crea col costruttore.
            Magazzino magazzino = new Magazzino(5);

            //5)Creazione MessageProducer e Receiver, per realizzare ricezione asincrona.
            QueueReceiver receiver = session.createReceiver(request); //Per ricevere i messaggi di risposta
            ListenerMagazzino listener = new ListenerMagazzino(magazzino, connection);
            receiver.setMessageListener(listener);

            System.out.println("[MAGAZZINO] Server avviato\n");

        }catch(NamingException ne){
            ne.printStackTrace();
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }    
    
}
