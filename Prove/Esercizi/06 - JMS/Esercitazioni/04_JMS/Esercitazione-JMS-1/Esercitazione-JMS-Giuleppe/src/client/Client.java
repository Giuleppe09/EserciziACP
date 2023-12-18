package client;

import java.util.Hashtable;
import java.util.Random;

import javax.naming.*;
import javax.jms.*;

public class Client {
    public static void main(String[] args) {
        //Configuriamo la tabella delle properties per JNDI.
        Hashtable<String,String> jndiProperties = new Hashtable<String,String>();
        jndiProperties.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory"); //Dove andare a prendere gli oggetti per creare il contesto iniziale.
        jndiProperties.put("java.naming.provider.url", "tcp://127.0.0.1:61616"); //Dove contattare il provider JMS, ActiveMQ sul port 61616.
        //P2P
        jndiProperties.put("queue.Request","request");
        jndiProperties.put("queue.Reply", "reply");

        try{
            //Configuro il contesto JNDI per poter lavorare con il provider ActiveMQ
            Context jndiContext = new InitialContext(jndiProperties);
            //Ottengo gli administered Object.

            System.out.println("[Client] Contesto JNDI correttamente settato.\n");

            //1) ConnectionFactory
            QueueConnectionFactory queueCF = (QueueConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
            //2) Ottengo le Destination.
            Queue reply = (Queue) jndiContext.lookup("Reply");
            Queue request = (Queue) jndiContext.lookup("Request");

            System.out.println("[Client] Ottenuti gli Administered Object.\n");

            //3)Connection
            QueueConnection connection = (QueueConnection) queueCF.createQueueConnection();
            System.out.println("[Client] Connessione con il provider ActiveMq riuscita correttamente.\n");
            //3.1) avvio la connessione per permettere la ricezione dei messaggi di risposta.
                connection.start();

            //4)Sessione
            QueueSession session = connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
            
            //5)Creazione MessageProducer e Receiver, per realizzare ricezione asincrona creeremo anche un Listener.
            QueueSender sender = session.createSender(request); //Per inviare i messaggi di richiesta
            
            QueueReceiver receiver = session.createReceiver(reply); //Per ricevere i messaggi di risposta
            Listener listener = new Listener();
            receiver.setMessageListener(listener);
            System.out.println("[Client] Listener settato correttamente.\n");
           
            //6)MapMessage
            MapMessage mex =  session.createMapMessage();
            mex.setJMSReplyTo(reply);
           


            String azione;
            int id_articolo;

            Random random = new Random();

            for(int i=0;i<10;i++){    
                id_articolo = random.nextInt(100)+1;
                if(random.nextInt(id_articolo)%2==0){
                    azione = "deposita";
                    mex.setString("operazione", azione);
                    mex.setInt("idArticolo",id_articolo);
                }else{
                    azione = "preleva";
                    mex.setString("operazione", azione);
                }

                sender.send(mex);
                if(azione.compareTo("deposita")==0){
                    System.out.println("[Client] Messaggio: ("+azione+", "+ id_articolo+")\n");
                
                }else{
                    System.out.println("[Client] Messaggio: ("+azione+")\n");
                }
            }
            
        }catch(NamingException ne){
            ne.printStackTrace();
        } catch (JMSException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }    
    
}
