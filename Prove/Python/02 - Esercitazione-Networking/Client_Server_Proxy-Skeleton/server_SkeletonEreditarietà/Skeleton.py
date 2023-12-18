import socket
import sys

from .workerThread import workerThread

sys.path.append('./service')
from service.service import IMessaggi
#Per ereditarietà

WORKERTHREAD = 'WorkerThread'

#in Java sarebbe public abstract class -name- implements service
class skeleton(IMessaggi): 
    def __init__(self,PORT):
        self.port = PORT
        self.address = ('localhost',self.port)

    def runSkeleton(self):
        serverSocket = socket.socket(socket.AF_INET,socket.SOCK_STREAM)

        serverSocket.bind(self.address)
        serverSocket.listen(5)
        print("[ServerSkeleton] In ascolto sul port: "+str(self.address[1]))

        while True:
            sock,addr = serverSocket.accept()
            wThread = workerThread(WORKERTHREAD,sock,self)
            wThread.start()


