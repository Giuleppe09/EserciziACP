package extractor;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;

public class ExtractorListener implements MessageListener {

    private Topic pressDest = null;
    private Topic tempDest = null;
    private TopicSession sessionPress = null; 
    private TopicSession sessionTemp = null;
    private TopicPublisher pubPress = null;
    private TopicPublisher pubTemp = null;

    public ExtractorListener(TopicConnection conn,Topic press,Topic temp){
        try {
            this.sessionPress = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);
        
            this.sessionTemp = conn.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

            this.tempDest = temp;
            this.pressDest = press;

            this.pubPress= this.sessionTemp.createPublisher(pressDest);
            this.pubTemp = this.sessionPress.createPublisher(tempDest);

        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Override
    public void onMessage(Message message) {
        
        //Unmarshalling

        MapMessage mex = (MapMessage) message;

        try {
            String type = mex.getString("type");
            int val = mex.getInt("value");

            TextMessage valore = null;

            if(type.compareTo("pressure")==0){
                valore = sessionPress.createTextMessage(Integer.toString(val));
                pubPress.publish(valore);
            }else{
                valore = sessionTemp.createTextMessage(Integer.toString(val));
                pubTemp.publish(valore);
            }

            System.out.println("[Listener] Pubblicato il valore: "+val+" sul topic: "+type);
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
        
    }
    
}
