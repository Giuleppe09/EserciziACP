package client;

import java.util.Hashtable;

import javax.naming.*;
import javax.jms.*;

public class Client{
    public static void main(String[] args){
        if(args.length!=2){
            System.out.println("[Client]: Command Line Arguments -> Dato - Port");
        }
        Hashtable<String, String> prop = new Hashtable<String,String>();
        prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
        prop.put("topic.storage", "storage");
        try{
            Context jndiContext = new InitialContext(prop);
            TopicConnectionFactory tcf = (TopicConnectionFactory) jndiContext.lookup("TopicConnectionFactory");
            Topic topic = (Topic) jndiContext.lookup("storage");
            TopicConnection tc = tcf.createTopicConnection();
            TopicSession ts = tc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            MapMessage mm = ts.createMapMessage();
            TopicPublisher pub = ts.createPublisher(topic);
            int dato = Integer.parseInt(args[0]);
            int port = Integer.parseInt(args[1]);
            if((dato >= 0) && (dato <= 100))
                System.out.println("[Client]: Dato immesso correttamente!\n");
            else
                System.out.println("[Client]: Dato fuori dal range consentito (0-100)!\n");            
            mm.setInt("dato", dato);
            mm.setInt("port", port);
            pub.publish(mm);
            System.out.println("[Client]: Client avviato correttamente, MapMessage pubblicato sul topic <storage> con contenuto ["+mm.getInt("dato")+" - "+ mm.getInt("port")+"]");

    

        }catch(JMSException e){
            e.printStackTrace();
        }catch(NamingException e){
            e.printStackTrace();
        }
    }
}