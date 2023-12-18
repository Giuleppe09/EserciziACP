package client;
import javax.naming.*;
import javax.jms.*;
import java.util.Hashtable;
import java.util.Random;

public class Client {
    public static void main(String[] args) {

        if(args[0] == null){
            return;
        }

        String printName = args[0];
        
        Hashtable<String,String> jndiProperties = new Hashtable<String,String>();
        jndiProperties.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        jndiProperties.put("java.naming.provider.url","tcp://127.0.0.1:61616");

        jndiProperties.put("queue.PrinterRequest","request");


        try {
            Context jndiContext = new InitialContext(jndiProperties);
            //Ottengo gli administeredObject
            QueueConnectionFactory queueCF = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
            Queue queue = (Queue) jndiContext.lookup("PrinterRequest");

            //Creo la connessione col provider activeMQ
            QueueConnection connection = queueCF.createQueueConnection();
            //Creo la sessione
            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            
            //Creo il Sender
            QueueSender sender = session.createSender(queue);

            //Creiamo i 5 MapMessage
            MapMessage mex = session.createMapMessage();
            Random random = new Random();
            
            for(int i=0;i<5;i++){
                mex.setString("nomeDocumento","doc"+random.nextInt(39)+1);
                mex.setString("nomePrinter",printName);

                sender.send(mex);
            }

            sender.close();
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
