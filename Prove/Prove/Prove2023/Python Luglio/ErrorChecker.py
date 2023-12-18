#Implementa la ricezione sulla STOMP Queue error e prevede come parametro di avvio
#(da terminale) una stringa tra fatal o exception. Alla ricezione di ciascun messaggio, il listener STOMP
#di Error Checker estrae il contenuto del messaggio, verifica se esso contiene la stringa ricevuta in input
#e, in caso affermativo, scrive su file (error.txt) e stampa a video il messaggio appena ricevuto

import stomp
import sys
import time

class ErrorListener(stomp.ConnectionListener):
    def __init__(self,nomeFile,tipoErrore):
        self.file = nomeFile
        self.tipoErrore = tipoErrore

    def on_message(self, frame):
        print("[Error]"+frame.body)

        if self.tipoErrore in frame.body :
            with open(file=self.file,mode="a") as file:
                file.write("\n"+frame.body)

        

def main(tipoErrore):
    addressProvider = [("127.0.0.1",61613)]
    connection = stomp.Connection(addressProvider)
    connection.connect(wait = True)
    errorListener = ErrorListener("error.txt",tipoErrore)
    connection.set_listener('ErrorListener',errorListener)
    connection.subscribe(destination='/queue/errorQueue',id=1,ack = "auto")

    while True:
        time.sleep(50)


if __name__ == '__main__':
    main(str(sys.argv[1]))