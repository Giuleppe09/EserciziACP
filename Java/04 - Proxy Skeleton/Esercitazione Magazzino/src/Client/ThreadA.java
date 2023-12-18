package Client;

import Service.IMagazzino;

public class ThreadA extends Thread {
    private IMagazzino proxy =null;

    public ThreadA(Proxy m){
        proxy = m;
    }

    public void run(){
        int id=0;
        String art;
        for(int i=0;i<3;i++){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            id = (int)(Math.random()*101)+1;


            if(id%2==0){
                art ="Laptop";
            }else{
                art = "Smartphone";
            }

            proxy.Deposita(art, id);
            
        }
    }
}
