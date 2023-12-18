package Server;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//Bisogna implementare la mutua esclusione

public class DispatcherImpl extends DispatcherSkeleton{
    
    //Bisogna implementare una coda circolare

    private int [] commands;
    private int head;
    private int tail;
    private int size;
    private int occ; //posti occupati

    private Lock l;
    private Condition SPZ_DISP;
    private Condition MSG_DISP;


    public DispatcherImpl(){

        head = 0;
        tail = 0;
        occ = 0;
        size = 5;      

        commands = new int[size];
        l = new ReentrantLock();
        SPZ_DISP = l.newCondition();
        MSG_DISP = l.newCondition();
    }

    
    private boolean full(){

        if(occ == 5){
            return true;
        }

        return false;
    }

    private boolean empty(){
        if(occ ==0){
            return true;
        }
        return false;
    }




    
    @Override
    public int getCmd() {
        int letto = -1;
        l.lock(); 
        //Acquisisco il lock sulla coda, che po in realtà non è sulla coda ma è sull'intero oggetto DispatcherImpl

        try {

            while(this.empty()){
                MSG_DISP.await();
            }

            letto = commands[head%size];
            head++;
            occ--;

            SPZ_DISP.signal(); //notifico
        
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally{
                l.unlock();
            }
        
        return letto;

    }

    @Override
    public void sendCmd(int command) {

        l.lock();
       try {
            while(this.full()){
                SPZ_DISP.await();
            }

        commands[tail%size] = command;
        tail++;
        occ++;

        MSG_DISP.signal();

        } catch (InterruptedException e) {
            
            e.printStackTrace();
        }finally{
            l.unlock();
        }

      
    }
    
}
