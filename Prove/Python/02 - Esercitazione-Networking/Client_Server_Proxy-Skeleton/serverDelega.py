import sys
from server_SkeletonDelega.serviceImpl import serviceImpl
from server_SkeletonDelega.skeleton import skeleton

def main(PORT):
    iMessaggiImpl = serviceImpl()
    sk = skeleton(PORT,iMessaggiImpl)
    sk.runSkeleton()
    



if __name__ == '__main__':
    try:
        if(len(sys.argv)>=1):
            PORT = int(sys.argv[1])
            main(PORT)
        else:
            print("Errore")

    except IndexError:
        print("Errore, esegui il main in questa forma: 'python .\server.py port '")
