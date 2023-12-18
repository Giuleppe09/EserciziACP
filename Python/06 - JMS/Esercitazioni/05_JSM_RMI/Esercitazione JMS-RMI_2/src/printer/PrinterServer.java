package printer;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import service.IPrinter;

public class PrinterServer{
    public static void main(String[] args) {
        
        if(args[0] == null){
            System.out.println("Errore, inserisci PrintName\n");
            return;

        }

        String name = args[0];
        System.out.println("[PrinterServer] PrinterName: "+name+"\n");

        //Esportiamo lo stub per poter effettuare il bind.
        IPrinter printer = new PrinterImpl(name);
        
        try {
            
            IPrinter printerStub = (IPrinter) UnicastRemoteObject.exportObject(printer,8900);
            Registry rmiRegistry = LocateRegistry.getRegistry();
            
            rmiRegistry.rebind(name, printerStub);

            System.out.println("[PrinterServer] "+name+" disponibile.");

        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}