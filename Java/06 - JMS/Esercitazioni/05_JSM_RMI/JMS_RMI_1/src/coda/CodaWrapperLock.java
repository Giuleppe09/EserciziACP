package coda;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CodaWrapperLock extends CodaWrapper {

    private ReentrantLock lock = null;
    private Condition MSG_DISP = null;
    private Condition SPZ_DISP = null;

    public CodaWrapperLock(ICoda coda) {
        super(coda);
        lock = new ReentrantLock();
        MSG_DISP = lock.newCondition();
        SPZ_DISP = lock.newCondition();
    }

    @Override
    public void deposito(int val) {
        try{
            lock.lock();

            while(coda.full()){
                SPZ_DISP.await();
            }
            
            coda.deposito(val);

            MSG_DISP.signal();

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    @Override
    public int preleva() {
        int val =-1;
        try{
            lock.lock();

            while(coda.empty()){
                MSG_DISP.await();
            }
            
            val = coda.preleva();

            SPZ_DISP.signal();

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            lock.unlock();
        }
        return val;
    }
    
}
