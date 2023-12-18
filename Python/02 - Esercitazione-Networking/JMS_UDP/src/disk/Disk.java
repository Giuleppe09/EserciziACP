package disk;
import java.util.Hashtable;

import javax.naming.*;
import javax.jms.*;

public class Disk{
    public static void main(String[] args){
        Hashtable<String, String> prop = new Hashtable<String,String>();
        prop.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        prop.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
        prop.put("topic.storage", "storage");
        try{
            Context jndiContext = new InitialContext(prop);
            TopicConnectionFactory tcf = (TopicConnectionFactory) jndiContext.lookup("TopicConnectionFactory");
            Topic topic = (Topic) jndiContext.lookup("storage");
            TopicConnection tc = tcf.createTopicConnection();
            tc.start();
            TopicSession ts = tc.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
            TopicSubscriber sub = ts.createSubscriber(topic);
            MyListener list = new MyListener();
            sub.setMessageListener(list);
            System.out.println("[Disk]: Listener avviato correttamente!\n");

        }catch(JMSException e){
            e.printStackTrace();
        }catch(NamingException e){
            e.printStackTrace();
        }
    }
}