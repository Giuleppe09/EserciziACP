/* Dispatcher Message Listener (`DispatcherMsgListener.java`):**
   - Il listener gestisce i messaggi ricevuti dalla coda di richieste.
   - In base al tipo di richiesta (deposito o prelievo), invoca il metodo corrispondente nel proxy per comunicare con il server Python attraverso il socket.*/ 

import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.TextMessage;
import javax.jms.Message;
import javax.jms.MessageListener;


public class DispatcherMsgListener implements MessageListener{
    private Queue replyQueue=null;
    private QueueConnection connection = null;
    private int portServer;

    public DispatcherMsgListener(Queue reply,String args,QueueConnection qC){
        this.replyQueue=reply;
        this.portServer=Integer.parseInt(args);
        this.connection = qC;
    }


    @Override
    public void onMessage(Message message) {
        TextMessage mex = (TextMessage) message; 
        Proxy proxy = new Proxy("127.0.0.1", this.portServer);
        System.out.println("[DispatcherListener] Messaggio ricevuto, mo c penz o thread.");

        DispatcherThread dThread = new DispatcherThread(proxy, this.connection,mex,this.replyQueue);
        dThread.start();
    }

}
