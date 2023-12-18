package dispatcher;

import java.rmi.RemoteException;
import java.util.Random;
import java.util.Vector;

import service.IDispatcher;
import service.IObserver;
import service.Reading;

public class DispatcherImpl implements IDispatcher {

    private Reading stato =null;
    private static final long serialVersionUID = 20L;
    private Vector<IObserver> observersT = null;
    private Vector<IObserver> observersP = null;

    public DispatcherImpl(){
        this.stato = new Reading(null, 0);
        this.observersP = new Vector<IObserver>();
        this.observersT = new Vector<IObserver>();
    }

    @Override
    public synchronized void setReading(Reading reading) throws RemoteException {
        String type = reading.getTipo();
        System.out.println("[DispatcherImpl] E' arrivata una lettura: ["+type+","+reading.getValore()+"]");
        
        this.stato = reading;

        Random random = new Random();
        try {

            Thread.sleep((random.nextInt(5)+1)*1000);

            if(type.compareTo("pressione")==0){

                if(observersP.size()!=0){
                    System.out.println("[DispatcherImpl] Ci sono degli Observer che sono interessati a questa lettura.");
                    
                    for(int i=0;i<observersP.size();i++){
                        observersP.get(i).notifyReading();
                    }

                }else{  
                    System.out.println("[DispatcherImpl] Non ci sono observer interessati.");
                }
            }else if(type.compareTo("temperatura")==0){ //Sarà il caso della temperatura dato che lato Generatore ci arrivano solo 2 tipi.
                
                if(observersT.size()!=0){
                    
                    System.out.println("[DispatcherImpl] Ci sono degli Observer che sono interessati a questa lettura.");
                    
                    for(int i=0;i<observersT.size();i++){
                        observersT.get(i).notifyReading();
                    }

                }else{
                    System.out.println("[DispatcherImpl] Non ci sono observer interessati.");
                }
            }
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }




    @Override
    public void attachObserver(IObserver callbackObject, String letturaInteresse) throws RemoteException {
        System.out.println("[DispatcherImpl] E' stata invocata una richiesta di attach di un observer interessato a: "+letturaInteresse);

        if( letturaInteresse.compareTo("temperatura")==0){

            this.observersT.add(callbackObject);
            System.out.println("[DispatcherImpl] Observer correttamente attached al vettore observersT.");

        }else if( letturaInteresse.compareTo("pressione")==0){
            
            this.observersP.add(callbackObject);
            System.out.println("[DispatcherImpl] Observer correttamente attached al vettore observerP.");    
        
        }else{
            System.out.println("[DispatcherImpl] NON E' STATO EFFETTUATO L'ATTACH: NON E' STATO RICONOSCIUTO UN TIPO VALIDO.");
        }   
        

    }

    @Override
    public Reading getReading() throws RemoteException {
        return this.stato;
    }
    
}
