import stomp
import multiprocessing as mp
import socket

# **Server Python (`server.py`):**
#  - Il server Python implementa un servizio (`Service`) con metodi `deposita` e `preleva`.
#  - Utilizza un skeleton (`ServiceSkeleton`) per gestire la comunicazione attraverso un socket e processa le richieste dei client attraverso un processo separato (`proc_fun`).
#  - Il server mantiene uno stato condiviso utilizzando una coda (`mp.Queue`) per gestire le richieste di deposito e prelievo.

class service():
    def prelievo(self):
        pass
    def deposito(self,val):
        pass

def proc_fun(sock,skeleton):
    request = sock.recv(1024).decode('utf-8')   
    
    method = request.split('#')[0].strip() #Metodo da invocare

    val = None #Reply da inviare al proxy.

    print("[ProcessoServer] Il metodo invocato è: "+method)

    if 'prelievo' in method:
        print('[ProcessoServer] Invoco il metodo prelievo sullo skeleton')
        val = skeleton.prelievo().encode('utf-8')
        
    elif 'deposito' in method: 
        parameter = request.split('#')[1]
        print('[ProcessoServer] Invoco il metodo deposito sullo skeleton')
        val = skeleton.deposito(parameter).encode('utf-8')
    else: 
        val ='metodo non valido'.encode('utf-8')

    

    reply = val
    

    sock.send(reply) #Già codificato in utf-8

    
    print("[ProcessoServer] Messaggio inviato al Proxy e socket chiusa.")


#Ereditarietà.
class ServiceSkeleton(service):

    def __init__(self,port):
        self.port = port
    def runSkeleton(self):
        serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
        serverSocket.bind(('localhost', self.port))
        serverSocket.listen(5)

        try:
            while True:
                soc, add = serverSocket.accept()
                process = mp.Process(target=proc_fun, args=(soc, self))
                process.start()
                
        except KeyboardInterrupt:
            pass  # Permette di chiudere il server correttamente se viene premuto Ctrl+C
        finally:
            serverSocket.close()


class serviceImpl(ServiceSkeleton):
    def __init__(self,queue,port):
        self.queue = queue
        super().__init__(port=port)

    def prelievo(self):
        print('[SERVER-IMPL] Invocato il prelievo')
        result = self.queue.get()
        print("[SERVER-IMPL] Prelevato", result)        
        return str(result)

    def deposito(self,data):
        print('[SERVER-IMPL] Invocato il deposito')
        self.queue.put(data)
        print("[SERVER-IMPL] Depositato", data) 
        return "Depositato"
      
        






def main():
    queue = mp.Queue(5)
    skeleton = serviceImpl(queue=queue,port=3000)
    skeleton.runSkeleton()

if __name__=='__main__':
    main()