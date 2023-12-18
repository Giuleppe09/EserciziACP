package proxy;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueSender;
import javax.jms.QueueSession;

public class ProxyThreadSender extends Thread{
    
    private QueueSession session;
    private Queue reply;
    private int value;


    public ProxyThreadSender(QueueSession session,int value, Queue replyQueue){
        this.session = session;
        this.reply = replyQueue;
        this.value = value;
        
    }

    public void run(){

        try {
			QueueSender sender = session.createSender(reply);
            MapMessage mex = session.createMapMessage();
            mex.setInt("value", value);
            sender.send(mex);

            System.out.println("[ThreadSender] Messaggio di risposta inviato:"+value);

            sender.close();
            session.close();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
}
