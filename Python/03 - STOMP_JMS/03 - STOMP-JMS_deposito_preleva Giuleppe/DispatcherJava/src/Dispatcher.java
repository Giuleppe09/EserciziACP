/***Dispatcher Java (`Dispatcher.java`):**
   - Il dispatcher Java crea una connessione JMS utilizzando ActiveMQ e ascolta la coda di richieste (`request`) per gestire le richieste provenienti dal client Python.
   - Quando riceve una richiesta, il dispatcher utilizza un listener (`DispatcherMsgListener`) per processare il messaggio e invoca un proxy (`DispatcherProxy`) per comunicare con il server Python tramite socket.
   - Il dispatcher invia poi la risposta al client Python tramite JMS sulla coda di risposte (`response`).*/

import javax.naming.*;

import java.util.Hashtable;

import javax.jms.*;

public class Dispatcher{
    public static void main(String[] args) {
        Hashtable<String,String> jndiProperties = new Hashtable<String,String>();
        jndiProperties.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        jndiProperties.put("java.naming.provider.url","tcp://127.0.0.1:61616");
        jndiProperties.put("queue.request","requestQueue");
        jndiProperties.put("queue.reply","replyQueue");

        try{    
            Context jndiContext = new InitialContext(jndiProperties);

            QueueConnectionFactory queueConnectionFactory = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
            Queue request = (Queue) jndiContext.lookup("request");
            Queue reply = (Queue) jndiContext.lookup("reply");


            QueueConnection queueConnection = queueConnectionFactory.createQueueConnection();
            queueConnection.start();

            QueueSession session = queueConnection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);

            /*
             * Ricezione asincrona
             */
            QueueReceiver queueReceiver = session.createReceiver(request);
            DispatcherMsgListener msgListener = new DispatcherMsgListener(reply,args[0],queueConnection);
            queueReceiver.setMessageListener(msgListener);





        }catch(NamingException ne){
            ne.printStackTrace();
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }



    }
}
