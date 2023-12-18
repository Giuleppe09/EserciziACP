#Esercizio Prod_Cons con Semafori

import threading 
import logging 
from package_coda.CodaWrapperSem import CodaWrapperSem
from Thread.consumatore import consuma
from Thread.produttore import producerThread


CONSUMER = 'CONSUMER'
PRODUCER = 'PRODUCER'

N_CONSUMERS = 10
N_PRODUCERS = 10
QUEUE_SIZE = 5

logging.basicConfig(level=logging.DEBUG,format='[%(threadName)-0s]%(message)s')

def main():
    producers = []
    consumers = []

    queue = CodaWrapperSem(QUEUE_SIZE)

    for i in range(N_CONSUMERS):
        cThread = threading.Thread(target=consuma   ##DELEGA
                                   ,name=CONSUMER+str(i),
                                   args=(queue,))
        consumers.append(cThread)
        cThread.start()

    for i in range(N_PRODUCERS):
        pThread = producerThread(queue,PRODUCER+str(i))        
        pThread.start()
        producers.append(pThread)


    for i in range (N_CONSUMERS):
        consumers[i].join()

    for i in range (N_PRODUCERS):
        producers[i].join()

if __name__=='__main__':
    main()