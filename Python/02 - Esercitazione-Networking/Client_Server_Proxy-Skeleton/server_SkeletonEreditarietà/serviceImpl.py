from .Skeleton import skeleton

class serviceImpl(skeleton): 

    def __init__(self,PORT):
        super().__init__(PORT)
    
    def invertiMex(self,message):
        stringaInversa = message[::-1]
        return stringaInversa