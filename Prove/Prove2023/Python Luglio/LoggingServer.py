import socket
import multiprocess as mp
import stomp
import time

BUFF_SIZE = 1024 # Per le socket recv

from ILogging import ILogging
#Abbiamm a ricevere po verimm camma fa.
#Skeleton per ereditarietà.

#I dati inseriti dalla coda, sono consumati da un processo consumatore avviato al lancio del Logging Server. 


#Runnable
def produttore(skeleton,conn):
    log = conn.recv(BUFF_SIZE).decode('utf-8')
    
    #ESEMPIO
    #messaggio = "minchia"+"#"+"tipo"
    #result = messaggio.split("#")
    #result = ["minchia","tipo"]

    # messaggioLog+"#"+str(tipo)
    parametri = log.split("#")
    skeleton.log(parametri[0],parametri[1])
       


    


class LoggingSkeleton(ILogging):

    def __init__(self):
        self.portService = 3000 #Proprio port.
        self.address = "127.0.0.1" #localhost 

    def runSkeleton(self):
        
        loggingServer = socket.socket(socket.AF_INET,socket.SOCK_STREAM)
        addressLoggingServer = (self.address,self.portService)
        loggingServer.bind(addressLoggingServer)

        print("[Logging Skeleton] In ascolto all'indirizzo: "+addressLoggingServer[0]+":"+str(addressLoggingServer[1]))
        loggingServer.listen(5)

        while True:

            print("[Logging Skeleton] In attesa di connessione..")            
            connection,address = loggingServer.accept()

            #Avvia produttore, ma forse questo lo fa il metodo log!
            Prod = mp.Process(target=produttore,name="Produttore",args=(self,connection,))
            Prod.start()

            print("[Logging Skeleton] E' arrivato un tentativo di connessione, ho avviato un thread produttore. ")
    
    def log(self,messaggioLog,tipo):
        pass


class LoggingImpl(LoggingSkeleton):

    def __init__(self,queue):
        super().__init__()
        self.queue =queue
    
    def log(self,messaggioLog,tipo):
        #Inserisce nella coda dei messaggi

        log = messaggioLog+"-"+str(tipo)
        self.queue.put(log)
        print("[LoggingImpl] Inserito il log: "+log)
        #Inseriamo di nuovo il messaggio intero, però al consumatore serve di nuovo smezzato



def consumatore(queue):

    addressProvider = [("127.0.0.1",61613)]
    connection = stomp.Connection(addressProvider)
    connection.connect(wait = True)
    while True:
        time.sleep(5) #
        stringa = queue.get()

        parametri = stringa.split("#")
        # messaggioLog+"#"+str(tipo)

        if parametri[1] == "1" or parametri[1] == "0":
            destination = "/queue/infoQueue"
        elif parametri[1] == "2":
            destination = "/queue/errorQueue"
        else:
            continue #passiamo all'iterazione successiva

        print(parametri[0] + parametri[1])
        print("[Consumatore] Inserisco nella coda:", destination)
        connection.send(destination, stringa)

def main():

    queue=mp.Queue(4)
    
    processoConsumatore = mp.Process(target=consumatore,name="ThreadConsumatore",args=(queue,))
    processoConsumatore.start()

    logging = LoggingImpl(queue)
    logging.runSkeleton()


    
    


if __name__ == '__main__':
    main()


