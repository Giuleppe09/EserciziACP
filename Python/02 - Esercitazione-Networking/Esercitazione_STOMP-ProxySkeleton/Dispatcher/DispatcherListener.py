import stomp
import multiprocess as mp
from ServiceProxy import ServiceProxy

#Implementazione per delega del processo, passo un callable object
def procFunction(proxy,mess):
    print("[ProcessoProxy] avviato.")

    #Unmarshalling del frame che è arrivato
    method = mess.split('#')[0]

    if(method=='prelievo'):
        result = proxy.prelievo()
        ##Invio indietro del risultato sulla coda reply
        
    else:
        parametro = mess.split('#')[1]
        result = proxy.deposito(parametro)

    print("[PROXY]",result)
     #Creiamo la connessione col broker activeMQ per ogni processo, perchè non è Thread-Safe STOMP
    connection = stomp.Connection([('127.0.0.1', 61613)])
    connection.connect(wait=True)
    connection.send(destination='/queue/response',body=result)
    print("[PROXY]tUTT'OK INVIATO SULLACODA REPLY")
    connection.disconnect()
    
        
# Nel metodo `on_message` della classe `MyListener`, creare un'istanza della classe `ServiceProxy` 
# e avviare un processo per gestire la richiesta utilizzae ndo la funzione `proc_fun`.

class DispatcherListener(stomp.ConnectionListener):
    
    def __init__(self,PORT,conn):
        self.connection = conn #Con cui manderemo indietro le risposte sulla coda /queue/reply
        #Dati da dare al Proxy per contattare il Server
        self.port = PORT
        

    def on_message(self, frame):
        #Creazione di ServiceProxy e assegno ad un processo
        print("[Proxy] Ricevuto messaggio, avvio processoProxy")
        proxy = ServiceProxy('127.0.0.1',self.port)

        #implementiamolo per delega
        p = mp.Process(target= procFunction,args=(proxy,frame.body))
        p.start()