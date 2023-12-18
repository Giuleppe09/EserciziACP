import socket
import sys
from .workerThread import workerThread
#Per delega

sys.path.append('./service')
from service.service import IMessaggi
WORKERTHREAD = 'WorkerThread'

#in Java sarebbe public class -name- implements service
class skeleton(IMessaggi): 

    def __init__(self,PORT,serviceImplementation):
        self.port = PORT
        self.address = ('localhost',self.port)
        self.oggettoImpl = serviceImplementation

    def runSkeleton(self):
        serverSocket = socket.socket(socket.AF_INET,socket.SOCK_STREAM)

        serverSocket.bind(self.address)
        serverSocket.listen(5)
        print("[ServerSkeleton] In ascolto sul port: "+str(self.address[1]))

        while True:
            sock,addr = serverSocket.accept()
            wThread = workerThread(WORKERTHREAD,sock,self)
            wThread.start()

    def invertiMex(self,mex):
        return self.oggettoImpl.invertiMex(mex)


