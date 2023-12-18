from .coda import coda
import threading


class codaWrapperLock(coda):
    
    def __init__(self,size):
        super().__init__(size)
        self.lock = threading.RLock()
        self.MSG_DISP = threading.Condition(lock=self.lock)
        self.SPZ_DISP = threading.Condition(lock=self.lock)

    def inserisci(self, elem):

        with self.lock: #lock().acquire()
        
            while(super().full()):
                self.SPZ_DISP.wait()
            
            super().inserisci(elem=elem)

            self.MSG_DISP.notify()

        #lock().release
        
       
    

    def preleva(self):

        with self.lock:

            while(super().empty()):
                self.MSG_DISP.wait()
            
            elem = super().preleva()
            self.SPZ_DISP.notify()
            return elem
        
        
    

