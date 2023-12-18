import multiprocess as mp
import socket

def proc_fun(messaggio,addrReturn,service):
    ##Fosse stato tcp avremmo passato la connection direttamente, ho deciso di suicidarmi.
    #Credo che devo ricreare un'altra socket per ogni processo, sennò comm facc.. abbuò almeno tengo addReturn
        sockProc = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)

        print("[ProcessWorkerServer] Avviato")
        messaggioDecodificato = messaggio.decode('utf-8')
        method = messaggioDecodificato.split('#')[0]

        if(method == 'prelievo'):
            reply = service.prelievo()
            

            
        else: 
            parameter = messaggioDecodificato.split('#')[1]
            reply = service.deposito(parameter)
            
        sockProc.sendto(reply.encode('utf-8'),addrReturn)
        
        print("[ProcessWorkerServer] Invio al client- c po foss o dispatcher- il risultato del metodo chiamato: "+method)
        
