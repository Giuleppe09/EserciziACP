package threadpipe; 

import java.io.*; 

/*
 Un WriterThread legge una stringa da System.in e la scrive su una pipe
 System.in è un oggetto di tipo InputStream, associato all'input da tastiera (di default).
 */




/*
 * La pipe è un'implementazione di PipedOutputStream e PipedInputStream
 */



public class WriterThread extends Thread { //Definiizione di un thread per ereditarietà 
	
	private DataOutputStream dataOut; //Sarà utilizzata per scrivere dati sulla PipedOutputStream
	/*
	 * DataOutputStream è sottoclasse di OutputStream che consente la scrittura
	 * dei tipi di dati primitivi (int double bool) in formato binario diretto, senza dover attuare alcuna
	 * procedura di conversione via codice, cosa che avremmo dovuto fare con OutputStream.
	 * 
	 * I dati comunque vengono inviati sempre in formato binario, ma usato DataOutputStream facilitiamo l'invio evitando la conversione.
	 */


	//COSTRUTTORE
	public WriterThread ( PipedOutputStream pipeOut ){
		dataOut  = new DataOutputStream ( pipeOut );	
		//in questo modo l'oggetto dataOut sarà in grado di scrivere sulla pipe specificata da pipeOut
	}
	
	

	/*
	 * Il thread leggerà continuamente da System.in, scrivendo la stringa
	 * letta sulla pipe sfruttando l'oggetto DataOutputStream
	 */
	public void run (){ 
		
		// BufferedReader è una classe utilizzata per leggere da un FLUSSO di INPUT, quale è System.in
		BufferedReader keyboardBuf = new BufferedReader ( new InputStreamReader ( System.in ) );
		//InputStreamReader è utilizzato per convertire un flusso di byte in un flusso di caratteri
				
		
		String s;
		
		while ( true ){ //Legge continuamente da System.in e scrive la stringa letta sulla pipe
			try{
				System.out.println ( "[Writer] enter string: "  );
				
				// lettura stringa
				s = keyboardBuf.readLine();
				System.out.println ( "[Writer] string: < " + s  + " > output to pipe");

				// output su pipe
				dataOut.writeUTF(s);
				
			}catch ( IOException e ){
				e.printStackTrace();
			}
		}	
	}

}
