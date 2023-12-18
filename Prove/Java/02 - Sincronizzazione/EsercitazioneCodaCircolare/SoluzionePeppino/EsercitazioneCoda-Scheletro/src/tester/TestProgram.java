package tester;

import coda.*;
import codaimpl.*;

public class TestProgram {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// Instanziare una coda circolare SENZA sincronizzazione
		Coda coda_senza_sincronizzazione = new CodaCircolare(5); //s è la dimensione della coda.
		
		// Instanziare uno dei 'wrapper' (decorator) responsabile della sincronizzazione
		CodaWrapperLock coda = new CodaWrapperLock(coda_senza_sincronizzazione);
		
		int nthreads = 100;
		WorkerThread[] workers = new WorkerThread[nthreads];
		// Creare un array di 100 thread
		

		for(int i=0; i<nthreads;i++){
			if(i%2==0){ 	// Instanziare ed avviare 50 thread di inserimento	
				
				workers[i] = new WorkerThread(coda, true); //2° parametro è il flag per capire se sono produttori o consumatori

			}else{// Instanziare ed avviare 50 thread di prelievo
				
				workers[i] = new WorkerThread(coda, false);
			
			}

			workers[i].start();
		}

			
		// Attendere la terminazione dei thread
		for(int i=0; i<nthreads;i++){
				try {
					workers[i].join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}	
		
	}
}
