package Shape;

import Interfacce.IShape;

public class Triangle implements IShape {

    @Override
    public void draw() {
        System.out.println(" /\\");
        System.out.println("/__\\");    
    }
 
    
}
