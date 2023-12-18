import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import service.IRider;

//Per delega...
public class RiderImpl implements IRider{

    private int location ;
    private String nameFile = null;


    public RiderImpl(int location, String nomeFile){
        this.location = location;
        this.nameFile = nomeFile;
    }

    @Override
    public int notifyOrder(int idOrder, String addr) {

        try {
            FileWriter fw = new FileWriter(nameFile,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            String ordine = idOrder+" "+addr;
            pw.write(ordine+"\n");
            
            pw.close();
            bw.close();
            fw.close();
            System.out.println("[Impl] Scrittura su file avvenuta.");

            return 1;   
            

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return -1;
        
    } 
    
}
