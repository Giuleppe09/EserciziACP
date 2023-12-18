package subscriberD;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import service.ISubscriber;

public class SubscriberImpl implements ISubscriber {
    private int idInteresse;
    private String nomeFile;

    public SubscriberImpl(int id, String nome) {
        this.idInteresse = id;
        this.nomeFile=nome;
    }

    @Override
    public void notifyAlert(int critically) {
        System.out.println("[Subscriber "+idInteresse+"] critically: "+critically+".");
        FileWriter fw;
        
        try {
        
            fw = new FileWriter(nomeFile,true);
        
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.append("Critically: "+critically+"\n");
            pw.flush();
            
            pw.close();
            bw.close();
            fw.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }    
}
