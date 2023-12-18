
#Server Implementation (Server.py):
#   - Implementare la classe `ServiceImpl` che eredita da `ServiceSkeleton`. Questa classe gestirà le richieste dei client.
#   - Implementare i metodi `deposita` e `preleva` della classe `ServiceImpl`, che manipoleranno una coda condivisa (`Queue`) per gestire le richieste dei client.
#   - La classe `ServiceSkeleton` deve implementare un metodo `run_skeleton` che crei e gestisca un socket, ascoltando le richieste dei client e avviando processi multipli per gestire ciascuna richiesta.

import sys
import socket
import multiprocess as mp
from processWorker import proc_fun
sys.path.append('./service')
from service import IService

BUFF_SIZE =1024

#abstract class skeleton implements(IService)
class Skeleton(IService):

    def __init__(self, port):
        self.port = port

    def runSkeleton(self):
        serverSocket = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
        address = ('127.0.0.1',self.port)

        serverSocket.bind(address)
       
        while(True):
            message,addrReturn =serverSocket.recvfrom(BUFF_SIZE)
            #avviamo il processo server che lo gestirà, implementato per ereditarietà.
            p = mp.Process(target=proc_fun, args=(message,addrReturn,self))
            p.start()
           

        serverSocket.close()

# Service Implementation
class ServiceImpl(Skeleton):

    def __init__(self,queue,port):
        super().__init__(port=port) #ho dovuto farlo perchè io gli dò il port da tastiera, altrimenti non ci sarebbe stato bisogno
        self.queue = queue 
    
    def deposito(self, data):
        self.queue.put(data)
        print("[SERVER-IMPL] Depositato", data)
        return "deposited"
    
    def prelievo(self):

        result = self.queue.get()
        print("[SERVER-IMPL] Prelevato", result)
        
        return str(result)


if __name__ == "__main__":

    try:
        PORT = sys.argv[1]
    except IndexError:
        print("Please, specify PORT arg")
    
    print("Server running ... ")

    # Create the Qeueue
    q = mp.Queue(5)

    # Create the Service and run the Skeleton
    serviceImpl = ServiceImpl(q,int(PORT))
    serviceImpl.runSkeleton()

