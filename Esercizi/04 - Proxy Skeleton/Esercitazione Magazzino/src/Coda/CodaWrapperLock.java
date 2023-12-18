package Coda;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import Service.ICoda;

public class CodaWrapperLock extends CodaWrapper{
    private Lock l;
    private Condition MSG_DISP;
    private Condition SPZ_DISP;

    public CodaWrapperLock(ICoda c){
        super(c);
        l=new ReentrantLock();
        MSG_DISP = l.newCondition();
        SPZ_DISP=l.newCondition();
    }    

    @Override
    public void inserisci(int id){
        
        l.lock(); //Acquisisco il lock della coda.

        try {

            while(coda.full()){ //Mi assicuro che la coda non sia piena
                
                SPZ_DISP.await();
          
            }
            
            coda.inserisci(id);
            MSG_DISP.signal();

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            l.unlock();
        }
    }


    @Override
    public int preleva() {
        l.lock();
        int x=0;
        try {
            while (coda.empty()) {
            
                    MSG_DISP.await();
            
            }

        x=coda.preleva();
        SPZ_DISP.signal();

        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }finally{
            l.unlock();
        }

        return x;

    }
}
