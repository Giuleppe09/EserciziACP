package magazzino;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;

import coda.ICoda;

public class Worker implements Runnable{
    private QueueConnection queueConnection;
    private MapMessage message;
    private ICoda coda;

    public Worker(QueueConnection con, Message mex,ICoda c){
        queueConnection=con;
        message = (MapMessage)mex;
        coda = c;
    }
    
    @Override
    public void run() {
        
        try {
            String azione = message.getString("operazione");
            int id = message.getInt("idArticolo");

            QueueSession session = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueSender sender = session.createSender((Queue)message.getJMSReplyTo());


            
            if(azione.compareTo("deposita")==0){
                System.out.println ( "	[MAGAZZINO-THREAD]: operazione = " + message.getString("operazione") 
						+ " , valore = " + message.getInt("valore"));
                coda.inserisci(id);
            }else{
                System.out.println ( "	[MAGAZZINO-THREAD]: operazione = " + message.getString("operazione") );
                TextMessage textMessage = (TextMessage)session.createTextMessage();
                textMessage.setText(Integer.toString(coda.preleva()));
                sender.send(textMessage);
            }

            sender.close();
            session.close();
            

        }catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
}
