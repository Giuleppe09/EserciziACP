package Client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import Service.IMagazzino;

//Nel Proxy va implementato il meccanismo di comunicazione
//con lo Skeleton.

//In questo caso sfruttiamo il protocollo di trasporto TCP/IP.

public class Proxy implements IMagazzino {
    //i dati membri sono l'indirizzo e il port del Server a cui collegarsi.
    //Faremo in modo che il Client inserisca tramite terminale queste informazioni.

    private int port;
    private String hostAddress;

    public Proxy(String ad,int p){
        this.port = p;
        this.hostAddress=ad;
    }

//Ad ogni chiamata istanziamo una connessione
    public void Deposita(String tipo,int id){
    
        try{
            System.out.println("[PROXY]: Tento la connessione a: "+hostAddress+":"+port);
            Socket socket = new Socket(hostAddress,port);
            System.out.println("[PROXY]: Connessione TCP stabilita");

            //out serve per scrivere dati sullo stream di output della connessione
            DataOutputStream out = new DataOutputStream(socket.getOutputStream()); //su cui scriveremo

           
            //Sfrutteremo lo StringTokenizer lato Server per l'unmarshalling
/* 
 * In questo caso supponiamo che la semplicità e la riduzione dell'overhead sono prioritari, dunque 
 * l'uso di un singolo pacchetto con delimitatore può essere una scelta valida.
 * 
 * In casi in cui vogliamo dare priorità alla flessibilità, conviene sfruttare più messaggi.
 */
            String request = new String("Deposita#"+tipo+"#"+id+"#");
            System.out.println("[PROXY]: Invio deposito di: ["+tipo+","+id +"].");
            out.writeUTF(request);
            System.out.println("[PROXY]: RICHIESTA INVIATA");
            
            socket.close();
        }catch(UnknownHostException err){
            //Eccezione sollevata se l'host specificato non è raggiungibile
            System.err.println("Host Sconosciuto");
        }catch(IOException io){
            //Eccezione sollevata se si verificano errori durante l'input/output, in particolare l'output
            System.err.println("Boh");
        }
    }

    public int Preleva(String tipo){
        int id;
          try{
            System.out.println("[PROXY]: Tento la connessione a: "+hostAddress+":"+port);
            Socket socket = new Socket(hostAddress,port);
            System.out.println("[PROXY]: Connessione TCP stabilita");


            //out serve per scrivere dati sullo stream di output della connessione
            DataOutputStream out = new DataOutputStream(socket.getOutputStream()); //su cui scriveremo
            DataInputStream in = new DataInputStream(socket.getInputStream());
           
//Sfrutteremo lo StringTokenizer lato Server per l'unmarshalling
/* 
 * In questo caso supponiamo che la semplicità e la riduzione dell'overhead sono prioritari, dunque 
 * l'uso di un singolo pacchetto con delimitatore può essere una scelta valida.
 * 
 * In casi in cui vogliamo dare priorità alla flessibilità, conviene sfruttare più messaggi.
 */
            String request = new String("Preleva#"+tipo+"#");
            System.out.println("[PROXY]: Invio della richiesta di Prelievo: ["+tipo+"].");
            out.writeUTF(request);
            System.out.println("[PROXY]: RICHIESTA INVIATA");
            

            id=in.readInt();
            
            System.out.println("E' stato prelevato l'articolo con id: "+id);

            socket.close();
        }catch(UnknownHostException err){
            //Eccezione sollevata se l'host specificato non è raggiungibile
            System.err.println("Host Sconosciuto");
        }catch(IOException io){
            //Eccezione sollevata se si verificano errori durante l'input/output, in particolare l'output
            System.err.println("Boh");
        }
        return 0;
    }

}
