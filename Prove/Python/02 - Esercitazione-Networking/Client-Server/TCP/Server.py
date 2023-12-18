import socket

def Server():
    PORT = 0  # prenderà il primo port disponibile
    address = 'localhost'
    
    BUFFER_SIZE = 1024

    # creazione della serverSocket TCP IPV4
    serverSocket = socket.socket(socket.AF_INET, socket.SOCK_STREAM) 
    
    # effettuiamo il bind.
    serverSocket.bind((address, PORT))

    #La vera port su cui è avvenuto il bind: 
    addr,curr_port = serverSocket.getsockname()
    #curr_PORT =serverSocket.getsockname()[1], dato che restituisce una tupla e vogliamo il secondo elemento che è il PORT.

    # lo poniamo in ascolto
    serverSocket.listen(1)

    print(f"Server in ascolto su {address}:{curr_port}")

    # quando una connessione viene accettata, vengono ottenuti l'oggetto socket, addr
    (clientSocket, addr) = serverSocket.accept()
    # il socket ottenuto rappresenterà la connessione tra Client e server

    print(f"Connessione accettata da: {addr}")

    data = clientSocket.recv(BUFFER_SIZE)
    print(f"Server ha ricevuto il messaggio: {data.decode('utf-8')} da: {addr}")

    ackMessage = "ack"
    clientSocket.send(ackMessage.encode("utf-8"))
    clientSocket.close()
    serverSocket.close()

if __name__ == "__main__":
    Server()
