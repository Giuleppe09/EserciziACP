package coda;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CodaWrapperLock extends CodaWrapper {

    private ReentrantLock lock;
    private Condition CMD_DISP;
    private Condition SPZ_DISP;

    public CodaWrapperLock(ICoda c) {
        super(c);
        lock = new ReentrantLock();
        CMD_DISP = lock.newCondition();
        SPZ_DISP = lock.newCondition();
        //TODO Auto-generated constructor stub
    }

    @Override
    public void inserisci(String cmd) {
        try{
            lock.lock();

            while(coda.full()){
                SPZ_DISP.await();
            }
            coda.inserisci(cmd);
            CMD_DISP.signal();

        }catch(InterruptedException ie){
            ie.printStackTrace();
        }finally{
            lock.unlock();
        }
        
    }

    @Override
    public String preleva() {
        String cmd=null;
         try{
            lock.lock();

            while(coda.empty()){
                CMD_DISP.await();
            }

            cmd = coda.preleva();

            SPZ_DISP.signal();


        }catch(InterruptedException ie){
            ie.printStackTrace();
        }finally{
            lock.unlock();
        }
        
        return cmd;
    }

   
    
}
