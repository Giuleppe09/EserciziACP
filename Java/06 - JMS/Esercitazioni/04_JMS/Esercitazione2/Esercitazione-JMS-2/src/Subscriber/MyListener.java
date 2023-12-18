package Subscriber;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import Coda.ICoda;

public class MyListener implements MessageListener {
    
    private ICoda coda;

    public MyListener (ICoda c ){
        this.coda= c;
    }

    @Override
    public void onMessage(Message m) {

        TextMessage message = (TextMessage) m;

        String mex;

        try {
        
            mex = message.getText();
     
        System.out.println("Letto il messaggio: "+mex);
        ThreadManager threadManager = new ThreadManager(mex,coda);
        threadManager.start();
        
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
}
