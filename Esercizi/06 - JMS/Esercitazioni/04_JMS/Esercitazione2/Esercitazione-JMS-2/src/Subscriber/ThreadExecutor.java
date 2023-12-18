package Subscriber;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import Coda.ICoda;

public class ThreadExecutor extends Thread{
    protected ICoda c;

    public ThreadExecutor(ICoda coda){
        super();
        this.c = coda;
    }


    //Funge da consumatore perenne
    public void run() {
        while(true){
            while(!c.empty()){

                String x = c.preleva();

                try{
                FileWriter fw = new FileWriter("cmdLog.txt",true);
                BufferedWriter bw = new BufferedWriter(fw);
                PrintWriter pw = new PrintWriter(bw);


                pw.write(x);
                

                }catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}   
