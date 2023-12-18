import socket
import threading 
from WorkerThread import threadFunction
import logging


logging.basicConfig(level=logging.DEBUG,format='[%(threadName)-0s] %(message)s')

if __name__ =='__main__':
    host = "localhost"
    PORT = 0
    serverAddress = (host,PORT)
    i=0 #ci servirà per dare un nome ai worker thread

    serverSocket = socket.socket(socket.AF_INET,socket.SOCK_STREAM) #IPV4 TCP
    serverSocket.bind(serverAddress)

    currentPort = serverSocket.getsockname()[1]

    print("Socket associata alla porta: ",currentPort)

    serverSocket.listen(5) #Il server ha una coda di massimo 5 processi alla volta che possono connettersi
    print("SERVER in attesa di connessioni..")


    while True:
        (sock,addr) = serverSocket.accept()

        print("Connessione con  ",addr[0],":",addr[1])

        threadWorker = threading.Thread(
                        target= threadFunction,
                        args=(sock,),
                        name = "WorkerThread["+str(i)+"]") #Per delega
        threadWorker.start()
