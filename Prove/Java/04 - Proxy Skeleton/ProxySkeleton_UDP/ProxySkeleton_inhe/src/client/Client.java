package client;

import service.Interface;

public class Client {
    public static void main(String[] args) {
        Interface service = new ClientProxy();
        int x =0;


        for(int i=0;i<100;i++){
            x=service.increment();
        }
                
        System.out.println("Il valore del contatore adesso è: "+ x);
        

    }
    
}