import threading
import socket
import logging

BUFFER_SIZE = 1024

logging.basicConfig(level=logging.DEBUG,format='[%(threadName)0s %(message)s]')

class clientThread(threading.Thread):
    def __init__(self,nome,serverPORT):
        super().__init__(name=nome)
        self.serverPort=serverPORT

    #Override
    def run(self):
        sock = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        address = ('localhost',self.serverPort)
        sock.connect(address)

        logging.debug("Connessione al server eseguita correttamente.")

        message = "DIO PORCODIO"

        sock.send(message.encode("utf-8"))
        logging.debug("Messaggio inviato.")

        risposta = sock.recv(BUFFER_SIZE).decode("utf-8")
        logging.debug("Messaggio capovolto dal server: "+risposta)

        sock.close()



