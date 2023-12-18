import sys
sys.path.append('./service')
from service.service import IMessaggi

class serviceImpl(IMessaggi):         
    
    def invertiMex(self,message):
        stringaInversa = message[::-1]
        return stringaInversa