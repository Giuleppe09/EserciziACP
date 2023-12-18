package Server;

public class Server {
    public static void main(String[] args) {
        DispatcherImpl impl = new DispatcherImpl();
        impl.runSkeleton();
    }
}
