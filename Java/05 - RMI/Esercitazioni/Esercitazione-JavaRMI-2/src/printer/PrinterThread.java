package printer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import service.IPrinter;

public class PrinterThread extends Thread{
    
    private Socket socket; //connessione.
    private IPrinter printer;
    
    
    private DataInputStream in;
    private DataOutputStream out;
    
    public PrinterThread(Socket sock,IPrinter prin){
        
        socket = sock;
        printer = prin;
       

        try {
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }   
    }


    public void run(){

        try{
        //Unmarshalling
        String method = in.readUTF();
        
        if(method.equals("print")){
            String docName = in.readUTF();

            boolean result = printer.print(docName); //Delega.

            out.writeBoolean(result);
            out.close();

            socket.close();
        

        }else{
            System.out.println("Errore, metodo non riconosciuto!");
            
        }

        }catch(IOException e ){

        }   
    }
}
    