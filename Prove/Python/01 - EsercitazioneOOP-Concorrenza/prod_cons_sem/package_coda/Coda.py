from package_coda.ICoda import ICoda

class Coda(ICoda):
    def __init__(self,size):
        self.size = size
        self.coda = [None]*self.size
        self.head = 0
        self.tail = 0
        self.occ =0

    def empty(self):
        return self.occ ==0

    def full(self):
        return self.occ == self.size

    def inserisci(self, elem):
        self.coda[self.head%self.size]= elem
        self.head+=1
        self.occ+=1

    def preleva(self):
        val = self.coda[self.tail%self.size]
        self.tail+=1
        self.occ-=1
        return val
    
    def getSize(self):
        return self.size               
