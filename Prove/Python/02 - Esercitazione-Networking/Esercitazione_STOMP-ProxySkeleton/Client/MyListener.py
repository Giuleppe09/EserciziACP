import stomp

class MyListener(stomp.ConnectionListener):
    def on_message(self, frame):
        
        print('[Client] Risposta: ',frame.body)