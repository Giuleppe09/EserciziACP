import stomp
import multiprocess
from matplotlib import pyplot as pl
import time

numValori = 0

queue = multiprocess.Queue(20)

class ListenerTemp(stomp.ConnectionListener):
    def __init__(self,queue):
        self.queue = queue
    
    def on_message(self, frame):
        print('[TempAnalyzer] Ho ricevuto una richiesta')
        valore = int(frame.body)
        
        global numValori
        numValori+=1
        self.queue.put(valore)
        print('[TempAnalyzer] Ho inserito un dato')
        

def main():
    global numValori
    connection = stomp.Connection([('127.0.0.1',61613)])
    connection.connect()
    list = ListenerTemp(queue)
    connection.subscribe(destination='/topic/tempTopic',id=1,ack='auto')
    connection.set_listener('ListenerTemperatura',listener=list)


    print('[TempAnalyzer] Aspettando i messaggi')
    while True:
        time.sleep(60)
        print("controlliamo cont: "+str(numValori))

        if(numValori == 20):
            numValori=0
            dati = []
            for i in range(queue.qsize()):
                dati.append(queue.get())

            pl.figure()#crea una nuova figura vuota

            #La variabile dati viene utilizzata come ordinata del grafico.
            pl.plot(range(len(dati)),dati)
            #range(len(dati)) genera una sequenza di numeri che rappresenta l'indice dei dati.

            pl.title('Valori di Temperatura')
            pl.xlabel('Numero Occorrenze')
            pl.ylabel('Valori Ricevuti')
            pl.show()



if __name__ =='__main__':
    main()