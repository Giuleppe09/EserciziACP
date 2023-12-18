import socket

def Server():
    
    PORT = 0
    address = 'localhost'
    BUFFER_SIZE = 1024

    serverSocket = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
    serverAddress = (address,PORT)
    serverSocket.bind(serverAddress)

    address,currentPort=serverSocket.getsockname()

    print("Server in ascolto sul currentPort: ",currentPort)

    message,addr =serverSocket.recvfrom(BUFFER_SIZE)

    print("Messaggio: ",message.decode("utf-8")," indirizzo client: ",addr)
    serverSocket.close()

if __name__=="__main__":
    Server()