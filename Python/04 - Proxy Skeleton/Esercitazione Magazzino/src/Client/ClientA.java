package Client;

public class ClientA {
    public static void main(String[] args) {
        Proxy m = new Proxy("127.0.0.1", 3000);

        ThreadA[] threads = new ThreadA[5];

        for(int i=0;i<5;i++){
            threads[i]=new ThreadA(m);
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
