package codaimpl;

import java.util.concurrent.locks.*;

import coda.*;

public class CodaWrapperLock extends CodaWrapper {

//Lock viene usata per creare un blocco di codice sincronizzato in modo che un solo thread alla volta possa accedere alla risorsa condivisa (coda)
	private Lock lock;

//Condition è usata per creare una condition variable, associato al blocco di codice sincronizzato
	private Condition empty;  //Per segnalare che la coda è vuota
	private Condition full; //Per segnalara che la coda è piena

	
	public CodaWrapperLock( Coda c ){
		super (c); //chiama il costruttore della classe genitore (non di c, ma passandogli c come argomento)
		//dunque richiama il costruttore di CodaWrapper
		
		lock = new ReentrantLock();
		
		// a differenza di synchronized, con i lock 
		// e' possibile creare N condizioni, mentre con synchronized si utilizza un'unica variabile condition che tra l'altro è pure implicita
		empty = lock.newCondition();
		full = lock.newCondition();
		
	}
	
	
	public void inserisci( int i){
		//Acquisisco l'accesso in mutua esclusione alla risorsa
		lock.lock();
		//Verifico se la coda è piena
		try{
		
			while ( coda.full() ){  
				try{
					empty.await(); //Se la coda è piena, thread in attesa sulla condizione empty.
				}catch ( InterruptedException e ){
					e.printStackTrace();
				}
			}
		//quando la coda non è più piena inseriamo e segnaliamo su full.
			coda.inserisci(i);
			full.signal();
			
		}finally{
			lock.unlock(); //rilasciamo il mutex, il lock associato all'oggetto.
		}		
	}
	
	
	public int preleva(){
		
		int x=0;
		
		lock.lock();
		
		try{
			
			while ( coda.empty()){
				try{
					full.await();
				}catch ( InterruptedException e ){
					e.printStackTrace();
				}		
			}
				
			x= coda.preleva();
			empty.signal();
		}
		finally{
			lock.unlock();
		}
		
		return x;
	}

	
}
