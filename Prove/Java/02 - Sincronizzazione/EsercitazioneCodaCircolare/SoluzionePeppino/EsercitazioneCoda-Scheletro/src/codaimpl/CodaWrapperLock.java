package codaimpl;

import coda.*;

public class CodaWrapperLock extends CodaWrapper {
	
	// Inserire Lock e variabili condition

	
	public CodaWrapperLock( Coda c ){
		super (c);
		
		// Inizializzare lock e variabili condition
		
	}
	
	
	public void inserisci( int i){
				
		
		// Implementare sincronizzazione con lock e variabili condition
		
			coda.inserisci(i);
				
	}
	
	
	public int preleva(){
		
		int x=0;
		
		// Implementare sincronizzazione con lock e variabili condition
				
			x= coda.preleva();
		
		return x;
	}

	
}
