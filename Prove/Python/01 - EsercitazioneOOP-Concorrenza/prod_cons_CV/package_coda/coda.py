from .ICoda import ICoda 
import logging
logging.basicConfig(level=logging.DEBUG, format='[%(threadName)-0s] %(message)s',)

class coda(ICoda):

    def __init__(self,dim):
        self.size = dim #QUEUE_SIZE
        self.codaCircolare = [None]*self.size  #Vettore vuoto         
        self.head = 0 #Testa coda per l'inserimento
        self.occ = 0 #numero di elementi
        self.tail = 0 #Tail coda per prelevare

    def getSize(self):
        return self.size

    def empty(self):
        return self.occ == 0

    def full(self):
        return self.occ == self.size

    def inserisci(self,elem):
        self.codaCircolare[self.head%self.size] = elem
        self.head = self.head+1
        self.occ = self.occ+1  
        logging.debug("Elemento inserito in coda.")
        

    def preleva(self):
        elem = self.codaCircolare[self.tail%self.size]
        self.occ+=-1
        self.tail+=1
        return elem

    