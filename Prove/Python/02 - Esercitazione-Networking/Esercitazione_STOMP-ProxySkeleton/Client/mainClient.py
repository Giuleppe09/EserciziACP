# **Client Implementation (client.py):**
#   - Creare una connessione STOMP verso il broker (IP: 127.0.0.1, Porta: 61613).
#   - Implementare un loop che invii richieste di deposito o prelievo al server attraverso la coda 'request'.
#   - Utilizzare la classe `MyListener` come listener per gestire le risposte del server sulla coda 'response'.

from random import randint
from MyListener import MyListener
import stomp
import time

def main():
    #Creiamo l'oggetto che rappresenta la connessione ad un server STOMP
    connection = stomp.Connection([('127.0.0.1',61613)]) 
    #Adesso invochiamo connect() su connection, per stabilire la connessione col server--> invio del frame STOMP CONNECT
    connection.connect(wait=True) #Cioè il metodo è bloccante finchè non c'è la risposta del servizio di messaging --> Conferma o Rifiuta connessione.


    #destination è la queue o topic a cui sottoiscriversi.
    #id è un identificativo univoco della sottoscrizione
    #ack è la modalità di acknowledgment

    for i in range(10): 
        scelta = randint(0, 100)
        if scelta % 2 == 0:
            message = 'prelievo'
        else:
            message = 'deposito#' + str(scelta)

        # Codifica il messaggio prima di inviarlo
        encoded_message = message.encode('utf-8')
        connection.send('/queue/request', encoded_message)
        
        print("[CLIENT] Request: ", message)

    
    #Listener
    list = MyListener()
    connection.set_listener('ListenerMinchione',list)
    connection.subscribe(destination='/queue/response',id=1,ack='auto')


    while True:
        time.sleep(60)

    connection.disconnect() #non avverrà mai ma vabby..


if __name__ == '__main__':
    main()