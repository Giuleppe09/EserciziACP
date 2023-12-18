package magazzino;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.QueueConnection;
import coda.ICoda;

public class ListenerMagazzino implements MessageListener{

    public ICoda riferimento; //Riferimento alla coda del magazzino da affidare ai threadWorker ad ogni messaggio ricevuto
    public QueueConnection connection;//per poter creare le sessioni, una per ogni thread per evitare i problemi di sincronizzazione cazzi e mazzi e


    public ListenerMagazzino(Magazzino magazzino,QueueConnection conn) {
        riferimento=magazzino.codaCircolare;
        connection = conn;
    }

    @Override
    public void onMessage(Message message) {

        Worker w = new Worker(this.connection,message,this.riferimento);
        Thread thread = new Thread(w);
        thread.start();

    }

    
}
