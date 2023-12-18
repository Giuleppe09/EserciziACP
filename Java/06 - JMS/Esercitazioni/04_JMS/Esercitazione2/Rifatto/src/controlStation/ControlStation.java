package controlStation;
import java.util.Hashtable;
import java.util.Random;

import javax.jms.*;
import javax.naming.*;

public class ControlStation{

    private static int N=3;
    public static void main(String[] args) {
        
        Hashtable<String,String> jndiProperties = new Hashtable<String,String>();
        jndiProperties.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		jndiProperties.put("java.naming.provider.url", "tcp://127.0.0.1:61616");

        jndiProperties.put("topic.Comandi","topic");

        try{
            Context jndiContext = new InitialContext(jndiProperties);
            
            //Ricavo gli administered Object
            TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory) jndiContext.lookup("TopicConnectionFactory");
            Topic topic = (Topic) jndiContext.lookup("Comandi");


            //Creazione connessione
            TopicConnection connection = topicConnectionFactory.createTopicConnection();

            //Creazione sessione
            TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);


            String comando=null;
            Random random = new Random();

            //Creazione Publisher
            TopicPublisher publisher = session.createPublisher(topic);
            
            //Creazione messaggio
            TextMessage message = session.createTextMessage();
            
            for(int i=0;i<N;i++){
                
                if((random.nextInt(3)+1)==1){
                    comando = "startSensor";
                }else if((random.nextInt(3)+1)==2){
                    comando = "stopSensor";
                }else if((random.nextInt(3)+1)==3){
                    comando = "read";
                }

                message.setText(comando);
                publisher.send(message);

            }

            publisher.close();
            session.close();
            connection.close();

        }catch(NamingException ne){
            ne.printStackTrace();
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    
    }
}

