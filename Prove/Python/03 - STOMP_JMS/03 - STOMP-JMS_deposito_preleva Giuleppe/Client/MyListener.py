import stomp

class MyListener(stomp.ConnectionListener):
    def on_message(self, frame):
        print('Risposta: ',frame.body)