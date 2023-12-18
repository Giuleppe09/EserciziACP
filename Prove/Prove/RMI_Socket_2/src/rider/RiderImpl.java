package rider;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import service.IRider;

public class RiderImpl implements IRider {
    private String nomeFile;

    public RiderImpl(String nF){
        nomeFile = nF;
    }


    @Override
    public void notifyOrder(int id, String address) {
        try{
            System.out.println("[RiderImpl]: Notifica dell'ordine con id <"+id+"> e indirizzo <"+address+">!\n");
            FileWriter fw = new FileWriter(nomeFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.write("Notifica dell'ordine con id <"+id+"> e indirizzo <"+address+">!\n");
            pw.close();
            bw.close();
            fw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
}
