package sportello;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import service.IGestoreSportelli;
import service.ISportello;

public class SportelloMain {
    public static void main(String[] args) {
        try {
            // Ottieni lo stub del gestore degli sportelli
            Registry registry = LocateRegistry.getRegistry();
            IGestoreSportelli gestoreStub = (IGestoreSportelli) registry.lookup("gestoreSportelli");

            // Crea e registra gli sportelli
            for (int i = 0; i < 3; i++) {
                ISportello sportello = new SportelloImpl(i);


                // Aggiungi lo sportello al gestore
                gestoreStub.attachSportello(sportello);

                System.out.println("[SportelloMain] Sportello " + i + " attached!");
            }

        } catch (RemoteException | NotBoundException ex) {
            ex.printStackTrace();
            System.err.println("[SportelloMain] Errore durante l'attach degli sportelli.");
        }
    }
}
