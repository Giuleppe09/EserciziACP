#per ereditarietà
from random import randint
import threading
import logging
import time


class producerThread(threading.Thread):
    def __init__(self,queue,name):
        super().__init__(name=name)
        self.queue=queue
    
    def run(self):
        logging.debug("Started")
        time.sleep(1.0)
        val = randint(0,100)
        self.queue.inserisci(val)
        logging.debug('Val: %r, inserito nella coda.',val)
    #%r è un tipo di rappresentazione