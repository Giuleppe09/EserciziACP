package logger;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class LoggerImpl extends LoggerSkel {

    public LoggerImpl(int port){
        super(port);
    }

    @Override
    public synchronized void registraDato(int dato) {
        System.out.print("[LoggerImpl]: Il dato registrato -> ["+dato+"]!\n");
        try{
            FileWriter fw = new FileWriter("Data.txt", true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);
            pw.write("Il dato registrato ->  ["+dato+"]!\n");
            pw.close();
            bw.close();
            fw.close();
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
}
