package service;

public interface IPrinter {

    public boolean print(String docName); //verrà invocato dal dispatcher sulle stampanti, verrà implementata nelle stampanti.
    
} 