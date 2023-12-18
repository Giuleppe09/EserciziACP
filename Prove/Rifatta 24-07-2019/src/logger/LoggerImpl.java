package logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LoggerImpl extends LoggerSkeleton {
    private String file;

    public LoggerImpl(int port,String nomeFile) {
        super(port);
        file = nomeFile;
    }

    @Override
    public synchronized void registraDato(int dato) {
    
        System.out.println("[LoggerImpl] Saving: "+dato+".");
       
            
       

        try {
            FileWriter fw = new FileWriter(file,true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.append("Saved: "+dato+"\n");
            pw.flush();
            Thread.sleep(2000);

            pw.close();
            bw.close();
            fw.close();
        
        System.out.println("[LoggerImpl] Scrittura avvenuta con successo!");    


        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }
    
}
