package Client;

import Service.IMagazzino;

public class ThreadB extends Thread{
    IMagazzino pr = null;


    public ThreadB(Proxy mag){
        this.pr=mag;
    }

    public void run(){
        int id =0;
        for(int i=0;i<3;i++){
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           int scelta=(int)(Math.random()*101)+1;


            if(scelta%2==0){
                id = pr.Preleva("Laptop");
                System.out.println("[CLIENTB] Prelevo [Laptop,"+id+"].");
                
            }else{
                id = pr.Preleva("Smartphone");
                System.out.println("[CLIENTB] Prelevo [Smartphone,"+id+"].");
            }
        }

    }

}

