package coda;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CodaWrapperLock extends CodaWrapper {
    private ReentrantLock lock;
    private Condition id_DISP;
    private Condition SPZ_DISP;


    public CodaWrapperLock(ICoda c) {
        super(c);
        lock = new ReentrantLock();
        id_DISP = lock.newCondition();
        SPZ_DISP = lock.newCondition();
    }

    @Override
    public void inserisci(int id_articolo) {
        
        try{
            lock.lock();

            while(coda.full()){
                SPZ_DISP.await();
            }

            coda.inserisci(id_articolo);

            id_DISP.signal();


        }catch(InterruptedException ie){
            ie.printStackTrace();
        }finally{
            lock.unlock();
        }
    }

    @Override
    public int preleva() {
        int val = -1;
        try{
            lock.lock();

            while(coda.empty()){
                id_DISP.await();
            }
            val = coda.preleva();
            SPZ_DISP.signal();


        }catch(InterruptedException ie){
            ie.printStackTrace();
        }finally{
            lock.unlock();
        }
        return val;
    }
    
}
