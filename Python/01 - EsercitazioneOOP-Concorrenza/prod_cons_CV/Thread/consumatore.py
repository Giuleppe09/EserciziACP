import threading
import logging
import time

from package_coda.ICoda import ICoda

class consumerThread(threading.Thread): #Implementazione per Ereditarietà.
    
    def __init__(self,queue,name):
        super().__init__(name=name)
        self.queue = queue #cioè gli passo il riferimento alla coda che verrà istanziata nel main.


    def run(self):
        logging.debug("\t\t\tStarted")
        time.sleep(1.0)
        val = self.queue.preleva() ##Funzionerà perchè l'oggetto che noi passiamo nel main è un oggetto codaWrapperLock.
        logging.debug('\t\t\tItem: %r, consumato.', val)
        