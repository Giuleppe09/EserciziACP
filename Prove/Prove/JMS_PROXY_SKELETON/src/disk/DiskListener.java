package disk;

import javax.jms.Message;
import javax.jms.MessageListener;

public class DiskListener implements MessageListener {

    @Override
    public void onMessage(Message m) {
        System.out.println("[MessageListener] Messaggio topicone!");
        DiskThread thread = new DiskThread(m);
        thread.start();
    }
}
