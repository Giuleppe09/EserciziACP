import sys
sys.path.append('./service')
from service import IService
import socket

BUFF_SIZE = 1024

#Implementa la comunicazione, usamm UDP per sbarià

class ServiceProxy(IService):

    def __init__(self,address,port): #Sarebbe l'indirizzo da contattare
        self.PORT = port#p sta sicur
        self.address = address

    def prelievo(self):
        print("[ServiceProxy] Prelievo invocato.")
        sock = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
        addressServer= (self.address, self.PORT)

        

        message = "prelievo"
        sock.sendto(message.encode("utf-8"),addressServer)
        print("[ServiceProxy] Invio richiesta prelievo..")

        data=sock.recv(BUFF_SIZE)
        
        sock.close()

        return data.decode('utf-8')
        
        
    def deposito(self,parameter):
        print("[ServiceProxy] Deposita invocato.")
        sock = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
        addressServer= (self.address, self.PORT)

        message = 'deposita#'+str(parameter)
        sock.sendto(message.encode('utf-8'),addressServer)
        print("[ServiceProxy] Invio richiesta deposito..")

        

        data = sock.recv(BUFF_SIZE) #messaggio depositato
        print(data.decode('utf-8'))
        sock.close()

        return data.decode('utf-8')

       
    