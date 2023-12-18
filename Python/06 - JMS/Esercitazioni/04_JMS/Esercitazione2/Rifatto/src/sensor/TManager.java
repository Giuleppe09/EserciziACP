package sensor;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.TextMessage;

import coda.ICoda;

    public class TManager extends Thread {
        private TextMessage message = null;
        private ICoda codaCircolare = null;
    
        public TManager(Message mex, ICoda coda) {
            super();
            // Assegna il parametro 'mex' all'attributo 'message'
            message = (TextMessage) mex;
            codaCircolare = coda;

        }
    
        @Override
        public void run() {
            String cmd = null;
    
            try {
                cmd = message.getText();
    
                System.out.println("[TManager] Inserisco cmd nella coda : " + cmd + "\n");
    
                codaCircolare.inserisci(cmd);
    
            } catch (JMSException e) {
                e.printStackTrace();
            }
        }
    }
    
