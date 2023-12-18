package Client;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import Interface.IDispatcher;


public class Actuator {
    //invoca getCmd ogni secondo, e lo scrive su CmdLog.txt

    public static void main(String[] args) {
    
        //Richiede l'oggetto Proxy
        IDispatcher proxy = new Proxy();
        int cmd=0;

        try{
        
        //FileOutputStream è utilizzato per creare un nuovo file o sovrascrivere uno esistente
        FileOutputStream file = new FileOutputStream("./cmdLog.txt");
        
        //PrintStream è la classe per poterci scrivere su un file.
        PrintStream outStream = new PrintStream(file);

        
            while(true){

                cmd = proxy.getCmd();  
                System.out.println("[ACT] Ricevuto comando "+cmd);
                outStream.println("Comando = "+cmd);

                Thread.sleep(1000);

            }            
        
    
        }catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
   
        }
    }