package client;

import java.util.Hashtable;
import java.util.Random;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.jms.TopicConnection;
import javax.jms.TopicConnectionFactory;
import javax.jms.TopicPublisher;
import javax.jms.TopicSession;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;


public class Client {

    public static void main(String[] args){
        //Da prompt viene inserito il type dei dati che verranno generati.

        if(args.length!=1 || (args.length==1 && (args[0].compareTo("temperature")!=0 && args[0].compareTo("pressure")!=0))){
            System.err.println("[Client] Errore, devi avviare il programma con la seguente sintassi: \n\t java nomePackage.Client type.");
            return;
        }

        String type = args[0];
        System.out.println("[Client] Avviato correttamente per generare richieste di tipo: "+ type);

        //Creiamo il contesto iniziale JNDI per poter poi andare ad utilizzare JMS
        Hashtable<String,String> jndiProperties = new Hashtable<>();
        jndiProperties.put("java.naming.factory.initial", "org.apache.activemq.jndi.ActiveMQInitialContextFactory");
        jndiProperties.put("java.naming.provider.url", "tcp://127.0.0.1:61616");
        jndiProperties.put("topic.data", "dataTopic");

        //creazione del contesto jndi da cui andremo a prelevare gli administeredObject e tramite cui andremo a connetterci con il provider ActiveMq
        try {
            Context jndiContext = new InitialContext(jndiProperties);
            System.out.println("[Client] Contesto creato correttamente.");

            //Ricaviamo gli administered objects.
            //1) Ricaviamo il TopicConnectionFactory impostato sulle proprietà con cui è inizializzato il contesto, dunque ricaviamo l'implementazione di queste classi.
            TopicConnectionFactory topicConnectionFactory = (TopicConnectionFactory) jndiContext.lookup("QueueConnectionFactory");
            
            //2) Ricaviamo la Destination, topic data.
            Topic topicData = (Topic) jndiContext.lookup("data");

            //3) Creazione della connessione
            TopicConnection topicConnection = topicConnectionFactory.createTopicConnection();

            //4)Creazione della session, contesto singleThreaded in cui andremo a creare un Publisher.
            TopicSession session = topicConnection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

            //5)Creazione del publisher, iniziandolo a inviare sulla destination.
            TopicPublisher publisher = session.createPublisher(topicData);

            //6)Creazione dei MapMessage e invio.
            Random random = new Random();
            int value =0;
            MapMessage request = session.createMapMessage();
            request.setString("type", type);

           

            for(int i=0;i<20;i++){
                Thread.sleep(2000); //Tra un invio e l'altro 2s di pausa.
                
                if(type.compareTo("pressure")==0){
                    value = random.nextInt(50)+1000; //in modo che generi valori tra 0 e 100.    
                }else{ //Sarà necessariamente la temperatura.
                    value = random.nextInt(101); //in modo che generi valori tra 0 e 100.
                }
                request.setInt("value", value);
                System.out.println("[Client] Invio della richiesta: ["+type+"]-"+"["+value+"]");
                
                //7)Pubblicazione della richiesta sul topic.
                publisher.publish(request);
            }

            publisher.close();
            session.close();
            topicConnection.close();
        

        } catch (NamingException e) {
            System.err.println("[Client] Errore nella creazione del contesto iniziale");
        } catch (JMSException e) {
            System.err.println("[Client] Errore di natura JMS.");
        } catch (InterruptedException e) {
            System.err.println("[Client] Errore dovuto a un'interruzione durante una sleep del thread main.");
        }

    }
}