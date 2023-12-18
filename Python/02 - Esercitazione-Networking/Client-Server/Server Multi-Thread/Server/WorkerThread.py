#Implementazione per delega, dunque solo il metodo
#supponiamo che il thread abbia il compito di invertire la stringa ricevuta dal cliente, sfruttando la notazione slicing e la manda indietro
import logging
BUFFER_SIZE = 1024

logging.basicConfig(level=logging.DEBUG,format='[%(threadName)0s %(message)s]')

def threadFunction(socket): #prende come argomento la socket, che rappresenta la connessione con il client.
    data = socket.recv(BUFFER_SIZE)
    message = data.decode("utf-8")
    message = message [::-1] #inverte la sequenza, step = -1

    print("Invertita: ",message)
    socket.send(message.encode("utf-8"))
    print("Inviata al client.")
    socket.close()


