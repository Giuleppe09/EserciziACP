import threading
from package_coda.Coda import Coda 

class CodaWrapperSem(Coda):
    def __init__(self,dim):
        super().__init__(size=dim)
        self.mutex = threading.Semaphore(1)  ##Semaforo per la mutua esclusione tra i diversi prod-cons
        self.SPZ_DISP = threading.Semaphore(self.getSize()) ##semaforo per la produzione, inizializzato a QUEUE_SIZE
        self.NUM_MSG = threading.Semaphore(0) ##Semaforo per la consumazione, inizializzato a 0 dato che non ci sono elementi.

    def inserisci(self, elem):
        #acquisiamo prima SPZ_DISP perchè se acquisissimo il mutex non rilasceremmo la coda in caso in cui non si vada avanti..

        self.SPZ_DISP.acquire() #per gestire la cooperazione tra i consumatori e i produttori
        
        with self.mutex: #per gestire la mutua esclusione tra i produttori.
            super().inserisci(elem=elem)

        self.NUM_MSG.release()
    
    def preleva(self):
        self.NUM_MSG.acquire() #non possiamo prelevare se non c'è niente

        with self.mutex: #gestiamo la mutua esclusione tra i consumatori.
            val = super().preleva()
        
        self.SPZ_DISP.release()
        return val