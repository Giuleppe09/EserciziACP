package dispatcher;
import javax.jms.*;

public class DispatcherListener implements MessageListener{

    @Override
    public void onMessage(Message mex) {
        MapMessage message = (MapMessage)mex;
        DispatcherThread thread = new DispatcherThread(message); 
        thread.start();
       
    }

}