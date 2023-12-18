package proxy;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;

import service.IService;

public class ProxyThreadReceiver extends Thread {
    
    private QueueConnection connection = null;
    private Queue request = null;
    private QueueSession session = null;
    private IService stub = null;

    public ProxyThreadReceiver(QueueConnection connection,Queue req,IService stub){
        this.connection = connection;
        this.request = req;
        this.stub = stub;
        try {
			this.session = this.connection.createQueueSession(false, Session.AUTO_ACKNOWLEDGE);
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    //In questa Session opera il ThreadReceiver


    //Qui ci sarà la ricezione asincrona.
    public void run(){
        System.out.println("[ProxyThreadReceiver] Attivato.");
        try {
			connection.start();
	
            ProxyListener listener = new ProxyListener(connection,stub);
        
			QueueReceiver receiver = session.createReceiver(request);
            receiver.setMessageListener(listener);
            System.out.println("[ProxyThreadReceiver] Attivato il MessageListener.");
        
        } catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
    }
}
