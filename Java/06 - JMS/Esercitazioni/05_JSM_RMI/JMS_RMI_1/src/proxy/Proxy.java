package proxy;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Hashtable;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import service.IService;

public class Proxy {
    public static void main(String[] args){
        //Invia N messaggi sulla coda Richiesta
        Hashtable<String,String> jndiProperties = new Hashtable<>();
        jndiProperties.put("java.naming.factory.initial","org.apache.activemq.jndi.ActiveMQInitialContextFactory");
		jndiProperties.put("java.naming.provider.url","tcp://127.0.0.1:61616");
        jndiProperties.put("queue.request", "requestQueue");

        try {

            Registry rmiRegistry = LocateRegistry.getRegistry();
            IService service = (IService) rmiRegistry.lookup("service");
            

            Context jndiContext = new InitialContext(jndiProperties);
        
            QueueConnectionFactory QCF = (QueueConnectionFactory) jndiContext.lookup("TopicConnectionFactory");
            Queue request = (Queue) jndiContext.lookup("request");
            
            QueueConnection connection = (QueueConnection) QCF.createConnection();
            
            //Genera un Thread receiver in ascolto(asincrona) su Request.
            ProxyThreadReceiver receiver = new ProxyThreadReceiver(connection,request,service);
            receiver.start();

        }catch(NamingException ne){
            ne.printStackTrace();

        } catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NotBoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }
    
}
