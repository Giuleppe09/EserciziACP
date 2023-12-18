package disk;

import javax.jms.*;

public class MyListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        MapMessage mm = (MapMessage) message;
        DiskThread dT = new DiskThread(mm);
        dT.start();
    }
}
