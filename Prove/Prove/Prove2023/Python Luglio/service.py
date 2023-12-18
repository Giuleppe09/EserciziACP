import socket
import sys
import time

from ILogging import ILogging
from random import randint

#proxy-skeleton con socket TCP per la comunicazione tra Service e Logging Server

def main():

    message = None
    tipo = None
    proxy = proxyService()

    for i in range(10):

        tipo = randint(0,2)

        val = randint(1,2) #Ci serve per decidere il tipo del messaggio

        if (tipo == 0 or tipo == 1):

            time.sleep(1)

            if(val == 1):
                message = "success"
            elif (val == 2):
                message = "checking"

        elif tipo == 2:
            if (val == 1):
                message = "fatal"
            elif (val == 2):
                message = "exception"

        print("[Service] Invio il log: ",message+str(tipo)) 
        proxy.log(messaggioLog=message,tipo = tipo)

            
class proxyService(ILogging):

    def __init__(self) -> None:
        self.portServer = 3000 #In modo che lo assegni da solo.
        self.address = 'localhost'
    
    def log(self,messaggioLog,tipo):
        sock = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        
        addressServer =(self.address,self.portServer)
        sock.connect(addressServer)

        mex = (messaggioLog+"#"+str(tipo)).encode('utf-8')
        sock.send(mex)

if __name__ == '__main__':
    main()