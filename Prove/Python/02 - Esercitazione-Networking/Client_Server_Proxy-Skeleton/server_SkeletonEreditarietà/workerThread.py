#Per ereditarietà
import threading
import socket

BUFFSIZE = 1024

class workerThread(threading.Thread):
    def __init__(self,name,sock,skeleton):
        super().__init__(name=name)
        self.s = sock
        self.skeleton = skeleton

    def run(self):
        print("[WorkerThread] avviato.")
        request = self.s.recv(BUFFSIZE).decode("utf-8")
        
        method = (request.split('#')[0])
        print("metodo: ",method)

        if(method == "invertiMex"):
            parametro = (request.split('#')[1])
            print("Stringa da invertire: ",parametro)

            reply = self.skeleton.invertiMex(parametro)
            
            self.s.send(reply.encode("utf-8"))

            self.s.close()