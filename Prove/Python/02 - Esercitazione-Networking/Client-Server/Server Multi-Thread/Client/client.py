import logging
import threading
import socket
import sys
from clientThread import clientThread
#Supponiamo di avere 10 Thread client, che fungeranno da nostri client, che andranno a fare richieste al Server.

logging.basicConfig(level=logging.DEBUG,format='[%(threadName)0s %(message)s]')

def main(PORT):
    serverPORT = (PORT)

    threads = []
    N_THREADS = 10
    CLIENT_THREAD = 'ClientThread'

    for i in range(N_THREADS):
        #Implementati per ereditarietà
        
        cThread = clientThread(CLIENT_THREAD,serverPORT)
        threads.append(cThread)
        cThread.start()        
    

    for i in range (N_THREADS):
        threads[i].join()


if __name__=='__main__':
    try:
        if len(sys.argv) >= 2:
            PORT = int(sys.argv[1])
            main(PORT)
        else:
            print("Errore, inserisci un PORT valido")
    except:
            print("ERRORE")