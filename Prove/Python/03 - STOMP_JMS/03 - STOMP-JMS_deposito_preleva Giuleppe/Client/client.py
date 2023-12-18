#**Client Python (`client.py`):**
#Comunicazione asincrona con il dispatcher, dunque c'è bisogno di creare un listener che gestisce le risposte da /queue/response
#In primis però il Client invia delle richieste di deposito o prelievo al dispatcher su una coda /queue/request

import stomp,time
from MyListener import MyListener

def main():
        #auto_content_lenght=FALSE P FORZ SENNO NON ARRIVANO I MESSAGGI BENE A JMS
        connection = stomp.Connection([('127.0.0.1', 61613)],auto_content_length=False)
        connection.connect(wait=True)

        listener = MyListener()
        connection.set_listener('listenerGay',listener)
        connection.subscribe(destination='/queue/replyQueue',id=1,ack='auto') #Ovviamente bisogna usare i nomi fisici delle Destination dichiarate con JMS.
        
        for i in range(4):
            if i %2==0:
                message = 'prelievo#'
            else: 
                message = 'deposito#'+str(i)

            print('Invio il messaggio di richiesta: ',message)
            connection.send('/queue/requestQueue',message)


        while True: #Ci vuole il while true comunque credo.. non basta settare il listener
            time.sleep(60)


if __name__=='__main__':
    main()