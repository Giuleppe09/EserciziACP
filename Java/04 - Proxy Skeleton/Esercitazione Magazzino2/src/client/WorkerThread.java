package client;

import java.util.Random;

public class WorkerThread extends Thread{
    
    private String method=null;
    private ProxyTCP proxy=null; 

    public WorkerThread(String method,ProxyTCP p){
        super();
        this.method=method;
        this.proxy=p;
        
    }

    public void run(){

        if(method.compareTo("preleva")==0){
            Random random = new Random();
            String art;
           
            for(int i=0;i<3;i++){
                int articolo = random.nextInt(100);
                
                if(articolo%2==0){
                    art = "laptop";
                }else{
                    art = "smartphone";
                }

                proxy.preleva(art);
            }
    


        }else if(method.compareTo("deposita")==0){
                Random random = new Random();
                String art;
           
                for(int i=0;i<3;i++){
                    int articolo = random.nextInt(100);
                    
                    if(articolo%2==0){
                        art = "laptop";
                    }else{
                        art = "smartphone";
                    }

                    proxy.deposita(art, articolo);
                }
            }
        }
}
