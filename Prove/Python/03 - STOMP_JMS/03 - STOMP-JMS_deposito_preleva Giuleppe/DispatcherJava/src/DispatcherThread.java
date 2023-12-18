import java.util.StringTokenizer;

import javax.jms.JMSException;
import javax.jms.QueueConnection;
import javax.jms.QueueSender;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.jms.Queue;

public class DispatcherThread extends Thread{
    private Proxy proxy= null;
    private QueueConnection queueConnection = null;
    private TextMessage txtMsg= null; //Da elaborare.
    private Queue reply = null;

    
    public DispatcherThread(Proxy p,QueueConnection qC, TextMessage mex,Queue replyQueue){
        this.proxy = p;
        this.queueConnection=qC;
        this.txtMsg = mex;
        this.reply = replyQueue;
    }

    public void run(){

        //Unmarshalling del messaggio
         try {
            String request = this.txtMsg.getText();
            System.out.println(request);

            StringTokenizer st = new StringTokenizer(request,"#");

            String method = st.nextToken();

            /*
             * Il proxy comunica solo verso il Server, dunque dovremmo occuparci qui nel thread della comunicazione verso il Client.
             */
            String result = null;
            QueueSession session = (QueueSession) queueConnection.createSession(false, Session.AUTO_ACKNOWLEDGE);
            QueueSender sender = session.createSender(this.reply);

            System.out.println("[DispatcherThread] Avviato e pronto a soddisfare la richiesta di : "+method+"\n");

            if(method.compareTo("prelievo")==0){    
               result= proxy.prelievo();
            }else if(method.compareTo("deposito")==0){
                int parameter = Integer.parseInt(st.nextToken());
                result=proxy.deposito(parameter);
            }else{
                System.out.println("Metodo non riconosciuto dal proxy.");
            }

            System.out.println("[DispatcherThread] In procinto di inviare il risultato ... "+result);

            TextMessage mex = session.createTextMessage(result);

            sender.send(mex);

            System.out.println("[DispatcherThread] Messaggio inviato sulla coda di risposta..");

        } catch (JMSException e) {
            System.out.println("Errore nella mex.getText()");
        }

    }
}
