package service;

public interface ILogger {

    public void registraDato(int dato); //Invocato dai thread Disk, eseguito in mutua esclusione.
    
}
