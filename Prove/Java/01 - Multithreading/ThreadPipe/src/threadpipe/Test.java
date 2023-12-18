package threadpipe;

import java.io.PipedOutputStream;

public class Test {
	
	public static void main(String[] args)  {
		// TODO Auto-generated method stub
	
		/*
		 * La classe PipedOutputStream
		 * è utilizzato per creare una pipe, che è un’implementazione 
		 * di un canale di comunicazione tra due thread. 
		 * La pipe è unidirezionale e può essere utilizzata per trasferire 
		 * dati tra due thread in esecuzione contemporaneamente all’interno
		 * della JVM.
		 * 
		 * La classe PipedOutputStream viene utilizzata per scrivere dati 
		 * sulla pipe. 
		 * 
		 * La classe WriterThread utilizza un
		 * oggetto DataOutputStream per scrivere dati sulla pipe,
		 * mentre la classe ReaderThread utilizza un oggetto DataInputStream
		 * per leggere dati dalla pipe.
		 */
		PipedOutputStream pipeOut = new PipedOutputStream();
		
		WriterThread w = new WriterThread (pipeOut);
		ReaderThread r = new ReaderThread (pipeOut);
		
		w.start();
		r.start();
		
		
	}

}
