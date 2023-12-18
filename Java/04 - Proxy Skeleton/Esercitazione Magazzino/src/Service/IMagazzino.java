package Service;

public interface IMagazzino {
    public void Deposita(String tipoArticolo,int id);
    //in funzione del tipo, l'id verrà inserito in una delle 2 code (laptop o smartphone)

    public int Preleva(String tipoArticolo);
}
