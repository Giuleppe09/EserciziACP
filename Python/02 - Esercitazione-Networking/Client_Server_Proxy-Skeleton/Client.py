import sys
from client.Proxy import Proxy

def main(PORT,stringa):
    
    portServer=PORT
    proxy = Proxy(portServer)

    print("[Client] Richiesta di inversione del serve")

    invertita =  proxy.invertiMex(stringa)

    print("[Client] La stringa invertita:",invertita)


if __name__=='__main__':
    try:
        if( len(sys.argv)>=2 ):
            PORT = sys.argv[1]
            message = sys.argv[2]
            main(PORT,message)
        else:
            print("Errore")
    except IndexError:
        print("Errore, esegui il main in questa forma: 'python .\Client.py port message'")