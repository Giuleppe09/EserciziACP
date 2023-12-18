
/* WorkerThread example */
//Definiamo un thread con la seconda soluzione, dunque creo l'implementazione del metodo run
//che andrà poi assegnato ad un thread.


class WorkerThread implements Runnable {  
    private String message;  

    public WorkerThread(String s){   //Costruttore
        this.message=s;  
    }  
    
    public void run() {   //semplicemente stampa il nome del thread corrente e il messaggio memorizzato
        
        System.out.println(Thread.currentThread().getName()+" (Start) message = "+message);  
        processmessage();//call processmessage method that sleeps the thread for 2 seconds  
        System.out.println(Thread.currentThread().getName()+" (End)");//prints thread name  
    }  
    
    public void processmessage() {   //sospende il thread per 2 secondi
        try {  Thread.sleep(2000);  } catch (InterruptedException e) { e.printStackTrace(); }  
    }  
}

