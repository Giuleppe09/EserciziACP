package printer;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import service.IDispatcher;
import service.IPrinter;

/**
 * La classe Printer rappresenta il punto di ingresso per avviare una stampante.
 * La stampante si connette al servizio di dispatcher utilizzando RMI e avvia uno skeleton per la comunicazione su socket.
 */
public class Printer {
    public static void main(String[] args) {
        try {

            // Verifica se sono stati forniti correttamente il numero di porta e il nome del file come argomenti da riga di comando
            if (args.length != 2) {
                System.out.println("Specificare correttamente il numero di porta e il file su cui scrivere!");
                return; // Esce se i parametri non sono stati forniti correttamente
            }

            // Estrae il numero di porta e il nome del file dalla riga di comando
            int port = Integer.parseInt(args[0]);
            String fileName = args[1];

            // Crea un'istanza dell'implementazione dell'interfaccia IPrinter
            IPrinter printer = new PrinterImpl(fileName);

            // Crea uno skeleton per la comunicazione su socket
            Skeleton ps = new Skeleton(printer, port);

            // Ottiene il riferimento al registro RMI
            Registry rmiRegistry = LocateRegistry.getRegistry();

            // Ottiene lo stub del servizio di dispatcher dal registro RMI
            IDispatcher stub = (IDispatcher) rmiRegistry.lookup("myDispatcher");

            // Registra la stampante presso il servizio di dispatcher tramite il metodo remoto addPrinter()
            // fornendo il numero di porta come parametro
            System.out.println("Aggiungo la stampante in ascolto su " + port);
            stub.addPrinter(port);

            // Avvia lo skeleton per la comunicazione su socket
            ps.runSkeleton();

        } catch (Exception e) {
            // Gestisce le eccezioni stampando la traccia dell'errore
            e.printStackTrace();
        }
    }
}
