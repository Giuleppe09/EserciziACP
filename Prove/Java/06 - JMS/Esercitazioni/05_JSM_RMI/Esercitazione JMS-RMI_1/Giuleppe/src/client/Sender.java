package client;

import java.util.Random;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;

public class Sender extends Thread {
    private QueueConnection connection =null;
    private Queue request = null;
    private Queue reply = null;

    public Sender(QueueConnection connection, Queue request,Queue reply){
        this.connection = connection;
        this.request = request;
        this.reply = reply;
    }
   
    public void run(){
            QueueSession session;
            try {
                session = (QueueSession) connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                QueueSender sender = session.createSender(request);
                MapMessage mex = session.createMapMessage();
                Random random = new Random();
                int value;
                String type;

                for(int i=0;i<10;i++){
                    value = random.nextInt(10);
                    
                    if(i%2==0){

                        type = "deposita";
                        mex.setString("tipo","deposita");
                        mex.setInt("value", value);
                        mex.setJMSReplyTo(reply);
                        System.out.println("[Client] E' stata inviata una richiesta di tipo: "+type+","+value+".");

                    }else{
                        
                        type = "preleva";
                        mex.setString("tipo","preleva");
                        System.out.println("[Client] E' stata inviata una richiesta di tipo: "+type);

                    }
                    sender.send(mex);
                    
                }
             } catch (JMSException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

    }
}
