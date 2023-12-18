package server_Delega;

import coda.CodaCircolare;
import coda.CodaWrapperLock;
import coda.ICoda;
import service.IMagazzino;

public class MagazzinoImpl implements IMagazzino{
    private ICoda codaLaptop;
    private ICoda codaSmartphone;
    
    public MagazzinoImpl(int size) {
        super();
        this.codaLaptop = new CodaWrapperLock(new CodaCircolare(size));
        this.codaSmartphone = new CodaWrapperLock(new CodaCircolare(size));
        //TODO Auto-generated constructor stub
    }

    @Override
    public void deposita(String articolo, int id) {
        if(articolo.compareTo("laptop")==0){
            codaLaptop.inserisci(id);
        }else{
            codaSmartphone.inserisci(id);
        }
    }

    @Override
    public int preleva(String articolo) {
       if(articolo.compareTo("laptop")==0){
        return codaLaptop.preleva();
       }else{
        return codaSmartphone.preleva();
       }
    }
  
}
