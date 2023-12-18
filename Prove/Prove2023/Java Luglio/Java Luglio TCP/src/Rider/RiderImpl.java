package Rider;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class RiderImpl extends RiderSkeleton{
    private String file= null;
    public RiderImpl(int port,String nomeFile) {
        super(port);
        file = nomeFile;
        //TODO Auto-generated constructor stub
    }

    @Override
    public int notifyOrder(int id, String address) {
        int result = -1;

        try {
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.append(address+" --> "+id);
            pw.flush();

            fw.close();

            result = 1;


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return result;
    }
    
}
