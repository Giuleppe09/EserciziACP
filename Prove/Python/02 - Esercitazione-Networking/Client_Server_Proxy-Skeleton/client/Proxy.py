import socket
from service.service import IMessaggi
BUFF_SIZE = 1024



class Proxy(IMessaggi):
    def __init__(self,serverPort):
        self.port = int(serverPort) #oggetto (PORT)    

    #Override
    def invertiMex(self,message):
        sock = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        serverAddress = (('localhost',self.port))
        sock.connect(serverAddress)
        print("[Proxy]Socket creata e connessa al server")

        method = 'invertiMex#'+message+''
        

        #si può fare tanto è TCP
        sock.send(method.encode("utf-8"))
        print("[Proxy] Metodo inviato al Server: "+method)

        print("[Proxy] In attesa della risposta del Server..")
        reply = sock.recv(1024).decode("utf-8")

        return reply
        


