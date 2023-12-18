#Implementa la ricezione sulla STOMP Queue info. Alla ricezione di ciascun messaggio, il
#listener STOMP di Info Filter estrae il contenuto del messaggio, se esso contiene il valore 1, allora
#scrive il contenuto del messaggio sul file info.txt (oltre che visualizzarla a video)

import stomp
import time

class InfoListener(stomp.ConnectionListener):
    def __init__(self,nomeFile):
        self.file = nomeFile

    def on_message(self, frame):
        print("[Info]"+frame.body)

        if "1" in frame.body:
            with open(file=self.file,mode="a") as file:
                file.write("\n"+frame.body)

        

def main():
    addressProvider = [("127.0.0.1",61613)]
    connection = stomp.Connection(addressProvider)
    connection.connect()
    infoListener = InfoListener("info.txt")
    connection.set_listener('InfoListener',infoListener)

    connection.subscribe(destination='/queue/infoQueue',id=1,ack = "auto")

    while True:
        time.sleep(50)


if __name__ == '__main__':
    main()