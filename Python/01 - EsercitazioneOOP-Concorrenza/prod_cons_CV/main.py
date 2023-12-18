###Esercizio Prod_Cons con condition variable.
 
import threading #Modulo per il supporto al multithreading.
import logging

from package_coda.codaWrapperLock import codaWrapperLock

from Thread.consumatore import consumerThread
from Thread.produttore import produciRandom

CONSUMER = 'Consumer' #Istanzieremo per Ereditarietà.
PRODUCER = 'Producer' #Istanzieremo per Delega.

N_CONSUMERS = 10
N_PRODUCERS = 10
QUEUE_SIZE = 5

#Configurazione del sistema di logging.
logging.basicConfig(level=logging.DEBUG,format = '[%(threadName)-0s]%(message)s',)


def main():
    producers = []
    consumers = []

    queue = codaWrapperLock(QUEUE_SIZE) 

    for i in range(N_CONSUMERS):
        ct = consumerThread(queue,CONSUMER)
        ct.start()

        consumers.append(ct)

    for i in range(N_PRODUCERS):
        pt = threading.Thread(
            target=produciRandom, #Runnable.
            name = PRODUCER + str(i),
            args =(queue,), #è una lista di argomenti
        )
        pt.start()
        producers.append(pt)
    


    
    # waiting consumers termination
    for i in range (N_CONSUMERS):
        consumers[i].join()


    # waiting producers termination
    for i in range (N_PRODUCERS):

        producers[i].join()


        
if __name__ == '__main__':
    main()


