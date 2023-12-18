package generator;

import java.rmi.RemoteException;
import java.util.Random;

import service.IDispatcher;
import service.Reading;

public class GeneratorRunnable implements Runnable{

    private IDispatcher stubDisp;

    public GeneratorRunnable(IDispatcher stub){
        this.stubDisp = stub;
    }

    @Override
    public void run() {

        System.out.println("[ThreadGenerator] Avviato.");

        Random r = new Random();
        
        int val;
        String type;
        
        try {
                
                
            for(int i = 0; i<3; i++){
            
                System.out.println("[ThreadGenerator] Genero l'istanza Reading..");
                val = r.nextInt(51);

                if(val%2==0){
                    type = "temperatura";
                }else{
                    type = "pressione";
                }

                System.out.println("[ThreadGenerator] Invoco il metodo setReading, con il Reading: ["+type+","+val+"].");
                Reading reading = new Reading(type,val);

                this.stubDisp.setReading(reading);
                Thread.sleep(5000);

            }   
        } catch (InterruptedException e) {
            System.out.println("[ThreadGenerator] ERRORE, ricevuta un'interrupt mentre il thread è in sleep!");
        } catch (RemoteException e) {
            System.err.println("[ThreadGenerator] Errore di natura RMI");
        }

    }
    
}
