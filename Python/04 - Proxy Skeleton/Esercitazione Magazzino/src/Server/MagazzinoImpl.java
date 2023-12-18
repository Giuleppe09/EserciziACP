package Server;

import Coda.CodaImpl;
import Coda.CodaWrapperLock;
import Service.ICoda;

public class MagazzinoImpl extends Skeleton{
    ICoda coda;
    CodaWrapperLock codaLaptop;
    CodaWrapperLock codaSmartphone;


    public MagazzinoImpl(){
        coda = new CodaImpl(5);

        codaLaptop = new CodaWrapperLock(coda);
        codaSmartphone = new CodaWrapperLock(coda);
    }

    @Override
    public void Deposita(String tipoArticolo, int id) {
        
        if(tipoArticolo == "Laptop"){

            codaLaptop.inserisci(id);

        }else if(tipoArticolo == "Smartphone"){
            codaSmartphone.inserisci(id);
        }

    }

    @Override
    public int Preleva(String tipoArticolo) {
        int id = 0;
        if(tipoArticolo =="Laptop"){
            id = codaLaptop.preleva();
        }else if(tipoArticolo == "Smartphone"){
            id = codaSmartphone.preleva();
        }
        return id;
    }
    
}
