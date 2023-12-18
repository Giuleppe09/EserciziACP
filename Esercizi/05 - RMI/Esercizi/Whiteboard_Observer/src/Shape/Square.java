package Shape;

import Interfacce.IShape;

public class Square implements IShape{

    @Override
    public void draw() {    
        System.out.println(" __");
        System.out.println("|__|");
    }
}

