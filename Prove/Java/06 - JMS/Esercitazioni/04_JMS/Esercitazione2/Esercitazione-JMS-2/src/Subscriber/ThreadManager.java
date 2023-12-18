package Subscriber;

import Coda.ICoda;

public class ThreadManager extends Thread{

    protected ICoda coda = null;
    private String command= null;


    public ThreadManager(String m, ICoda c){
        super();
        this.coda = c;
        command = m;
    }


    //Lavorano da produttori.
    public void run(){ //Devono inserire nella coda

        coda.inserisci(command);
        System.out.println("[TManager] Inserimento nella coda: "+command);
    }
}
