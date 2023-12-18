import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;


/*
 * 4. **Dispatcher Proxy (`DispatcherProxy.java`):**
   - Il proxy gestisce la comunicazione con il server Python attraverso un socket.
   - Invia le richieste di deposito o prelievo al server Python e riceve le risposte.
   - Dopo aver ottenuto la risposta dal server Python, invia la risposta al client Python tramite JMS. --> L'ho fatto fare ai thread.
 */


public class Proxy implements IService{
    private int p;
    private String ad =null;

    public Proxy(String addressServer,int portServer){
        this.p = portServer;
        this.ad = addressServer;
    }

    public String prelievo(){
        String result=null;
        try {
            Socket socket = new Socket(this.ad,this.p);

            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
           
            /*
             * PROBLEMA SU COME VENGONO RICEVUTI I DATI, NON POSSO USARE IL METODO SOLITO..
             *DataInputStream input = new DataInputStream(socket.getInputStream());
             result = input.readUTF()
             Non si può usare perchè readUTF si aspetta un formato di dati specifico che include la lunghezza della stringa della
             parte di dati, che non è compatibile col modo in cui Python codifica le stringhe UTF-8
             */

            // NOTE: A BufferedReader is used to receive data from a Python application, since it allows using the readLine method 
		
            /*
             * InputStreamReader è un ponte da flussi di byte a flussi caratteri, decodificandoli in caratteri.
             * Buffered Reader legge il testo da un flusso di input di caratteri, permettendo una lettura di caratteri efficiente.
             * 
             */
        
            BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            String method = "prelievo#";

            output.writeUTF(method); //codificato in utf-8
            System.out.println("[Proxy] Richiesta di prelievo inviata al server.\n[Proxy]In attesa di una risposta..");
            result = (input.readLine()); //già decodificato in utf-8    na comunque sarà un messaggio di testo

            
            System.out.println("[Proxy] Risultato inviato al Client..");

            socket.close();
            


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;

    }

    public String deposito(int val){
        String result =null;
        try {
            Socket socket = new Socket(this.ad,this.p);
            DataOutputStream output = new DataOutputStream(socket.getOutputStream());
            
            // NOTE: A BufferedReader is used to receive data from a Python application, since it allows using the readLine method 
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));


            String method = "deposito#"+val;

            output.writeUTF(method); //codificato in utf-8
            System.out.println("[Proxy] Richiesta di deposito inviata al server.\nIn attesa di una risposta..");
            result = input.readLine(); //già decodificato in utf-8   
            System.out.println("[Proxy] Esito inviato al Client..");
            
            socket.close();



        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return result;
    }

}
