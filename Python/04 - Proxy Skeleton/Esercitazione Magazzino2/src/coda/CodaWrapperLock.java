package coda;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class CodaWrapperLock extends CodaWrapper {
    
    private ReentrantLock lock=null;
    private Condition SPZ_DISP=null;
    private Condition MSG_DISP=null;


    public CodaWrapperLock(ICoda c){
        super(c);
        lock = new ReentrantLock();
        SPZ_DISP = lock.newCondition();
        MSG_DISP = lock.newCondition();
    }

    public void inserisci(int id){

        try{
            lock.lock();

            while(coda.full()){
                
                try{
                    SPZ_DISP.await();
                    
                }catch(InterruptedException ie){
                    System.err.println("Errore nella wait su SPZ_DISP");
                }
            }
            
            coda.inserisci(id);
            MSG_DISP.signal();

        }finally{
            lock.unlock();
        }

    }

    public int preleva(){
        int val=-1;
        try{
            lock.lock();
      
            while (coda.empty()) {
                try{
                    MSG_DISP.await();
                }catch(InterruptedException ie){
                    System.err.println("Errore nella wait su SPZ_DISP");
                }
            }

            val = coda.preleva();

        }finally{
            lock.unlock();
        }
        
        return val;

    }



}
