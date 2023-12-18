import logging
from random import randint
import time
#Implementazione per delega.

#implementazione per delega.
def produciRandom(queue): #Runnable, produzione di un valore random

    logging.debug("Started")

    val=randint(0,100) 
    time.sleep(1.0)
    queue.inserisci(val)
    logging.debug('Val: %r, Inserito nella coda.',val)


