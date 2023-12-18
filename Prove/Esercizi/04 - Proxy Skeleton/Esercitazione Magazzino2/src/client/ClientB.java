package client;

public class ClientB {
    public static void main(String[] args) {
       
        ProxyTCP proxy = new ProxyTCP("3000");
        WorkerThread[] threads = new WorkerThread[5];
        for(int i=0;i<5;i++){
            threads[i] = new WorkerThread("preleva",proxy);
            threads[i].start();
        }

        for(int i=0;i<5;i++){
            try{
                threads[i].join();
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }
    
}
