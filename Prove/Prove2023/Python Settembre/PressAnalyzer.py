import stomp, csv, time

class MyListener(stomp.ConnectionListener):

    def __init__(self,count):
        self.count = count
    
    def on_message(self, frame):
        print('[PressAnalyzer] Ho ricevuto una richiesta')
        self.count +=1
        value = frame.body
        intValue = int(value)

        with open('press.csv', mode='a', newline='') as pressures:
            writer = csv.writer(pressures) #creazione del writer
            writer.writerow([self.count,intValue]) #scrivo la riga


    
if __name__ == "__main__":

    counter = 0
    conn = stomp.Connection([('127.0.0.1', 61613)])

    conn.set_listener('listener', MyListener(conn,counter))
    conn.connect(wait=True)
    conn.subscribe(destination='/topic/pressTopic', id=1, ack='auto')

    print('[PressAnalyzer] Aspettando i messaggi')

    while True:
        time.sleep(20)
