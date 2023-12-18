package Subscriber;

import java.util.Hashtable;

import javax.naming.*;

import Coda.CodaImpl;
import Coda.CodaWrapperLock;
import Coda.ICoda;

import javax.jms.*;


public class Sensor{

    public static int D=5;

    public static void main(String[] args) {
        
        Hashtable<String,String> properties = new Hashtable<String,String>();
        properties.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        properties.put("java.naming.provider.url","172.0.0.1:61616");
        
        properties.put("topic.commands","newCommands");

        try{
        Context jndiContext = new InitialContext(properties);

        //Lookup Administered Object - (ConnectionFactory,Destination)
        TopicConnectionFactory topicCF = (TopicConnectionFactory) jndiContext.lookup("TopicConnectionFactory");    
        Topic topic = (Topic) jndiContext.lookup("command");

        //Creazione della connessione
        TopicConnection connection = (TopicConnection) topicCF.createTopicConnection();
        connection.start();
       
        //Creazione della session
        TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        
        
        //Creazione coda in cui inserire comandi
        ICoda codaCircolare = new CodaWrapperLock(new CodaImpl(D)); 

        //Ricezione asincrona
        TopicSubscriber sensor = session.createSubscriber(topic);
        MyListener listener = new MyListener(codaCircolare); //Passiamo al Listener la Coda che verrà passata poi ai Manager.
        sensor.setMessageListener(listener);

        
        ThreadExecutor thr = new ThreadExecutor(codaCircolare);
        thr.start();
            

        } catch (NamingException e) {
            // TODO: handle exception
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}