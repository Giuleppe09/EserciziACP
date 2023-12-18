package server_Ereditarietà;


public class Magazzino{
    public static void main(String[] args) {
        
        System.out.println("[Server] Avvio il Magazzino.");
      
        MagazzinoImpl magazzino = new MagazzinoImpl(5);
        magazzino.runSkeleton();

    }
}