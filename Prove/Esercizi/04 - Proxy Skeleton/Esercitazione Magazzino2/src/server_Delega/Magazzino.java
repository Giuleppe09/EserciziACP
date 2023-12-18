package server_Delega;

public class Magazzino {
    public static void main(String[] args) {
        
        MagazzinoImpl mag = new MagazzinoImpl(5);
        SkeletonMagazzinoD skeleton = new SkeletonMagazzinoD(mag);
        skeleton.runSkeleton();

    }
    
}
