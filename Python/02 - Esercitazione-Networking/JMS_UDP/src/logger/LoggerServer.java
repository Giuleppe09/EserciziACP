package logger;

public class LoggerServer {
    public static void main(String[] args){
        if(args.length!=1){
            System.out.println("[LoggerServer]: Command Line Argument -> Port");
        }
        int port = Integer.parseInt(args[0]);
        LoggerSkel skel = new LoggerImpl(port);
        skel.runSkeleton();
    }
}
