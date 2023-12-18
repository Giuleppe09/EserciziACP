package Client;

public class Client{
    
    public static void main(String[] args) {

        for(int i=0;i<5;i++){
            ThreadClient thr = new ThreadClient();
            thr.start();
        }
    }
}