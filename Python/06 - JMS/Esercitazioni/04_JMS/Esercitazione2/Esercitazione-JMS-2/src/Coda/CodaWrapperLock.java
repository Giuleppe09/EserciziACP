package Coda;

import java.util.concurrent.locks.*;

public class CodaWrapperLock extends CodaWrapper {
	
	private Lock lock;
	private Condition empty;
	private Condition full;

	
	public CodaWrapperLock( ICoda c ){
		super (c);
		
		lock = new ReentrantLock();
		
		// a differenza di synchronized, con i lock 
		// e' possibile creare N condizioni 
		
		empty = lock.newCondition();
		full = lock.newCondition();
		
	}
	
	
	public void inserisci( String i){
				
		lock.lock();
		
		try{
		
			while ( coda.full() ){
				try{
					empty.await();
				}catch ( InterruptedException e ){
					e.printStackTrace();
				}
			}
				
			coda.inserisci(i);
			full.signal();
			
		}finally{
			lock.unlock();
		}		
	}
	
	
	public String preleva(){
		
		String x=null;
		
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
