package Publisher;

import javax.naming.*;

import java.util.Hashtable;
import java.util.Random;

import javax.jms.*;

public class ControlStation{
    public static int N =20;

    public static void main(String[] args) {
        
        Hashtable<String,String> properties = new Hashtable<String,String>();

        properties.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFaftory");
        properties.put("java.naming.provider.url","127.0.0.1:61616");
        properties.put("topic.commands","newCommands");


        //Non credo serva rimettere il topic

        try{
            Context jndiContext = new InitialContext(properties);

//Lookup Administered Object - (ConnectionFactory,Destination)
            TopicConnectionFactory topicCF = (TopicConnectionFactory) jndiContext.lookup("TopicConnectionFactory");
            Topic topic = (Topic) jndiContext.lookup("command");

//Creazione della connessione
            TopicConnection connection = (TopicConnection) topicCF.createTopicConnection();
//Creazione della session
            TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
//Creazione TopicPublisher
            TopicPublisher publisher = session.createPublisher(topic);

            Random r = new Random();
            
            TextMessage message = session.createTextMessage();

            for(int i=0;i<N;i++){
                int command = r.nextInt(3)+1;
                if(command ==1){
                    message.setText("startSensor");
                }else if(command == 2){
                    message.setText("stopServer");
                }else{
                    message.setText("read");
                }
                publisher.send(message);
            }


            publisher.close();
            session.close();
            connection.close();


        }catch(NamingException e){
            System.err.println("Problemi nella creazione del Contesto JNDI");

        } catch (JMSException e) {
            // TODO Auto-generated catch block

            System.err.println("Errore nella creazione della connessione con ActiveMQ");
            e.printStackTrace();
        }
    }

}