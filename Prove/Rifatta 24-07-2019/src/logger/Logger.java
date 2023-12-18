package logger;

public class Logger {
    public static void main(String[] args){

        if(args.length!=1){
            return;
        }

        int port = Integer.parseInt(args[0]);
        System.out.println("[loggerServer] Avviato e in ricezione sul port: "+port);
        
        LoggerImpl logger = new LoggerImpl(port, "salvataggio.txt");
        //per ereditarietà lo skeleton
        logger.runSkeleton();
    } 
}
