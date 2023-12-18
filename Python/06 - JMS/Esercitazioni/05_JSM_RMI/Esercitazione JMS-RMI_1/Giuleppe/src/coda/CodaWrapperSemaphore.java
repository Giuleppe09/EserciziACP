package coda;
import java.util.concurrent.Semaphore;


public class CodaWrapperSemaphore extends CodaWrapper {

    private Semaphore mutex = null;
    private Semaphore MSG_DISP = null;
    private Semaphore SPZ_DISP = null;

    public CodaWrapperSemaphore(ICoda coda) {
        super(coda);
        mutex = new Semaphore(1);
        MSG_DISP = new Semaphore(0);
        SPZ_DISP = new Semaphore(5);
        //TODO Auto-generated constructor stub
    }


    @Override
    public void deposito(int val) {
        
        try {
			SPZ_DISP.acquire();
		

			mutex.acquire();

            coda.deposito(val);
            MSG_DISP.release();

            mutex.release();

            

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

    }

    @Override
    public int preleva() {
        int val = -1;
        
        try {
            MSG_DISP.acquire();
    
			mutex.acquire();

            val = coda.preleva();
            SPZ_DISP.release();
            
            mutex.release();

            

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return val;
    }

}