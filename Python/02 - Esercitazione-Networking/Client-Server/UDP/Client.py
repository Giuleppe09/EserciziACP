import socket 
import sys

def Client(PORT):

    message = "puozz passa nu uaij"
    serverAddress = ("localhost",PORT)
    s = socket.socket(socket.AF_INET,socket.SOCK_DGRAM)
    s.sendto(message.encode("utf-8"),serverAddress)

    s.close()


if __name__ == "__main__":

    try:
        PORT = sys.argv[1]
        
        Client(int(PORT))

    except IndexError:
        print("Specifica un port valido.")

    