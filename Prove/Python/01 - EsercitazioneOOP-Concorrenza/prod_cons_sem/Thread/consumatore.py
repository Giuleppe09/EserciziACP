#per delega.
#Dunque definiamo il "runnable", la funzione che andrà ad eseguire il thread
import logging
import time ##Ha più senso che la sleep sia nedl thread.


def consuma(queue):
    logging.debug("Started")
    time.sleep(1.0)
    val= queue.preleva()
    logging.debug('Val: %r, prelevato dalla coda.',val)

    return val