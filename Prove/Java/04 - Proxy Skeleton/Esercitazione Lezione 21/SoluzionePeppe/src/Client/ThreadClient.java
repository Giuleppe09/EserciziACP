package Client;

import java.util.Random;

import Interface.IDispatcher;

public class ThreadClient extends Thread{
    private IDispatcher proxy = null;

    //ogni 2-4 secondi invoca sendCMD con cmd tra [0-3]
    public ThreadClient(){
        super(); //invochiamo il costruttore di Thread
    }


    public void run(){
        proxy = new Proxy(); 
 
        //ma istanziando qui il proxy, c'è la problematica che se il Server cambia le sue impostazioni di connessione
        //si sminchia tutto? Chissà. Magari mettendolo nel while ad ogni send andava a ristabilire la connessione ma si consumano un sacco
        //di risorse.0
    
    
        while(true){

            Random rand = new Random();
            
            int cmd = rand.nextInt(4);
            //nextInt genera un numero casuale tra 0 e 3.

            int second = rand.nextInt(3)+2;
            //+2 perchè così ricaviamo un numero tra 2 e 4.

            proxy.sendCmd(cmd);
            System.out.println("[ThreadClient]: Ho inviato " + cmd);

            try {
                //Argomento è espresso in millisecondi.
                sleep(second * 1000);

            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
