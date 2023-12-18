package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import coda.ICoda;
import service.IService;

public class ServerImpl extends UnicastRemoteObject implements IService{

    private ICoda coda = null;

	protected ServerImpl(ICoda queue) throws RemoteException {
		super();
        coda = queue;
		//TODO Auto-generated constructor stub
	}

	@Override
	public void deposita(int val) throws RemoteException {
        System.out.println("[Impl] Richiesta di deposito.");
		coda.deposito(val);
        System.out.println("[Impl] Deposito avvenuto con successo.");
	}

	@Override
	public int preleva() throws RemoteException {
        System.out.println("[Impl] Richiesta di prelevamento.");
		int val = coda.preleva();
        System.out.println("[Impl] Richiesta di prelievo completata.");
        
        return val;
    }
    
}
