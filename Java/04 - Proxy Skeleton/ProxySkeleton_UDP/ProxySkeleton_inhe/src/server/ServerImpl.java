package server;

public class ServerImpl extends ServerSkeleton {
    int x;

    ServerImpl(){

    }

    public synchronized int increment(){
        return ++x;
    }

}
