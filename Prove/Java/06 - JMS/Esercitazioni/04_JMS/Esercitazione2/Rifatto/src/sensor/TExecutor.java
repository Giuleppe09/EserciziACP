package sensor;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import coda.ICoda;

public class TExecutor extends Thread {

    private ICoda coda=null;

    public TExecutor(ICoda c){
        super();
        coda=c;
    }
    @Override
    public void run() {

        try{
            FileWriter fw = new FileWriter("cmdLog.txt",true); 
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            
       
            while(true){

                Thread.sleep(10*1000);

                while(!coda.empty()){
                System.out.println("[TExector] PULISCO LA CODA\n");
                String cmdPrelevato =coda.preleva();
                System.out.println(cmdPrelevato);

                

                pw.append("cmd-"+cmdPrelevato+"\n");
                pw.flush();
                }
                       
            }

               
        

        } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
        } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();

        }
    }
}
