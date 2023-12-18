import socket
import sys

def client(PORT, MESSAGE):
    IP = 'localhost'
    BUFFER_SIZE = 1024

    # Creiamo una socket TCP con IPv4
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

    try:
        print(f"Provo a connettermi a {IP}:{PORT}")
        
        # Connettiamoci al server
        s.connect((IP, PORT))
        print(f"Connesso a {IP}:{PORT}")

        # Inviamo il messaggio al server, convertito in bytes
        print(f"Invio il messaggio: {MESSAGE}")
        s.send(MESSAGE.encode("utf-8"))

        # Riceviamo i dati dal server
        data = s.recv(BUFFER_SIZE)
        print(f"Dati ricevuti dal server: {data.decode('utf-8')}")

    except ConnectionRefusedError:
        print("Connessione rifiutata dal server.")
    except UnicodeEncodeError:
        print("Il messaggio contiene caratteri non validi.")
    except socket.error as e:
        print(f"Errore di socket durante la connessione al server: {e}")
    except Exception as e:
        print(f"Altra eccezione durante la connessione al server: {e}")

    finally:
        # Chiudiamo la socket client per terminare la comunicazione
        if s:
            s.close()
            print("Connessione chiusa")

if __name__ == "__main__":
    try:
        # Otteniamo la porta e il messaggio da riga di comando
        PORT = int(sys.argv[1])
        MESSAGE = sys.argv[2]
    except (IndexError, ValueError):
        print("Specifica PORT Server e MESSAGGIO da inviare correttamente.")
        sys.exit(1)

    client(PORT, MESSAGE)
