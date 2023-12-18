package Client;

public class ClientB {
    public static void main(String[] args) {

        Proxy p = new Proxy("127.0.0.1",3000);
        ThreadB[] threads = new ThreadB[5];

        for(int i=0;i<5;i++){
            threads[i]=new ThreadB(p);
            threads[i].start();
        }

        for(int i=0;i<5;i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }


        
    }

    
}
