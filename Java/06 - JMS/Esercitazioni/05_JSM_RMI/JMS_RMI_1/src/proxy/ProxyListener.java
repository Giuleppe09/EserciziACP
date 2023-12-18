package proxy;

import java.rmi.RemoteException;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueSession;
import javax.jms.Session;

import service.IService;

public class ProxyListener implements MessageListener{

    private QueueConnection connection;
    private IService stub;

    public ProxyListener(QueueConnection connection,IService stub){
        this.connection = connection;
        this.stub = stub;
    }

	@Override
	public void onMessage(Message message) {

        MapMessage mex = (MapMessage) message;
        Queue replyQueue = null;

        //Se un messaggio è di tipo preleva, ha nel campo header JMS Reply proprio la destination a cui tornare il risultato.

        try {
			String type = mex.getString("tipo");
            System.out.println("[Listener] Messaggio arrivato: "+type+".");
            
            if(type.compareTo("preleva")==0){

                replyQueue = (Queue) mex.getJMSReplyTo();
                //Preleva dallo stub.
                int val = stub.preleva();
                //Creazione del threadSender, e quindi di una nuova session in cui opererà il thread sender.
                QueueSession session = (QueueSession) connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                ProxyThreadSender senderThread = new ProxyThreadSender(session,val, replyQueue);
                senderThread.start();

            }else if(type.compareTo("deposita")==0){
                int value = mex.getInt("value");
                stub.deposita(value);
            }

		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        
		System.out.println("");
	}
    
    
}
