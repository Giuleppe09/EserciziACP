# **Dispatcher Implementation (dispatcher.py):**
#  - Implementare la classe `MyListener` che eredita da `stomp.ConnectionListener`. 
#    Questa classe gestirà le richieste ricevute dal client attraverso il broker STOMP.

import stomp
import sys
import time
from DispatcherListener import DispatcherListener

def main(PORT):

    try:
        
        print("[Dispatcher] running ... ")
        #Creiamo la connessione col broker activeMQ
        connection = stomp.Connection([('127.0.0.1', 61613)])
        print("[Dispatcher] Connessione creata con il provider ActiveMQ")

        #Abilitiamo la connessione
        connection.connect(wait=True)
        print("[Dispatcher] Connessione correttamente stabilita!")

    except Exception as e:
        print(f"Errore durante la connessione al broker: {str(e)}")
        sys.exit(1)

   
    #Creiamo il listener
    dispatcherListener = DispatcherListener(PORT=PORT,conn=connection)
    connection.set_listener('ListenerFrocio',dispatcherListener)
    connection.subscribe(destination='/queue/request',id=1,ack='auto')
    
    print("[Dispatcher] Waiting for request ... ")

    # Keep the listener active
    while True:
        time.sleep(60)


if __name__=='__main__':
    try:
        PORT = int(sys.argv[1])
        main(PORT)
    except IndexError:
        print("Please, specify PORT arg")
    
    

