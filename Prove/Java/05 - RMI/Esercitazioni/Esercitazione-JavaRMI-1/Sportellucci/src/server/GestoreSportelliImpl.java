package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

import service.IGestoreSportelli;
import service.ISportello;

public class GestoreSportelliImpl extends UnicastRemoteObject implements IGestoreSportelli{
    
    private static final long serialVersionUID = 20L;

    private Vector<ISportello> sportelliAttached=null; //Lista di sportelli.

    protected GestoreSportelliImpl() throws RemoteException {
        super();
        sportelliAttached = new Vector<ISportello>();
    }

    
    public boolean sottoponiRichiesta(int idCliente) throws RemoteException{

        System.out.println("[GestoreSportelli] E' stata ricevuta una richiesta dal client con id["+idCliente+"]..\n");
        boolean richiestaServita=false;

        for (int i = 0; i < sportelliAttached.size() && (!richiestaServita); i++) {
            richiestaServita = sportelliAttached.get(i).serviRichiesta(idCliente);
        }
        System.out.println("[Gestore] Richiesta da "+idCliente+" terminata con esito "+richiestaServita);

        

        return richiestaServita;

    }

    public void attachSportello(ISportello sportello) throws RemoteException { //Verrà invocato dagli sportelli.

        System.out.println("[GestoreSportelli] Invocato attachSportello..\n");
        sportelliAttached.add(sportello);
        System.out.println("[GestoreSportello] Sportello ("+sportello.getId()+")");

    }

}
