import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * Sfruttiamo un thread pool con un numero di thread fissi, di dimensione 5, per eseguire 10 thread.
 */

public class Test {

    public static void main(String[] args) {

        //ExecutorService estende l'interfaccia Executor e fornisce metodi aggiuntivi per controllare lo stato dei Thread.
        ExecutorService executor = Executors.newFixedThreadPool(5); //crea un pool di thread con dimensione fissa 5.

        //Ciclo for che ad ogni iterazione crea un WorkerThread con messaggi diversi, eseguito dall'ExecutorService.
        for (int i = 0; i < 10; i++) {
            Runnable worker = new WorkerThread("" + i);
            executor.execute(worker); //execute accetta solo runnable.
        }

        executor.shutdown(); //Per terminare l'esecuzione del pool di thread.
        while (!executor.isTerminated()) { // per bloccare il thread main fino a quando tutti i thread non sono completati.
        }
        System.out.println("Finished all threads");
    }
 
}