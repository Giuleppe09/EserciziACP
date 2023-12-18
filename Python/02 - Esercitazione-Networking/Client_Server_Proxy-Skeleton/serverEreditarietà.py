import sys
from server_SkeletonEreditarietà.serviceImpl import serviceImpl


def main(PORT): 
    iMessaggi = serviceImpl(PORT=PORT)
    iMessaggi.runSkeleton()




if __name__ == '__main__':
    try:
        if(len(sys.argv)>=1):
            PORT = int(sys.argv[1])
            main(PORT)
        else:
            print("Errore")

    except IndexError:
        print("Errore, esegui il main in questa forma: 'python .\server.py port '")
