package Controller;

import Model.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ObjectCreator {
    // Factory, cria novos elementos seguindo algumas regras

    private Screen screen;
    
    public ObjectCreator(Screen s) {
        this.screen = s;
    }
    
    public void putObject(char elementId, int x, int y) {
        Element obj = null;
        switch (elementId) {
            case 'h':
                obj = Bomberman.getInstance();
                break;
            case 'W':
                obj = new UndestroyableWall();
                break;
            case 'w':
                obj = new DestroyableWall(0);
                break;
            case 'b':
                obj = new DestroyableWall(1);
                break;
            case 'f':
                obj = new DestroyableWall(2);
                break;
            case 'd':
                obj = new DestroyableWall(3);
                break;
            case 's':
                obj = new DestroyableWall(4);
                break;
            case 'B':
                obj = new Bat();
                break;
            case 'R':
                obj = new Radio();
                break;
        }
        if (obj != null) {
            obj.setPosition(x, y);
            screen.addElement(obj);
        }
    }
    
    private static void writeElement(Element e, String filename) {
        FileOutputStream file = null;
        try {
            file = new FileOutputStream("elements/" + filename);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ObjectCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ObjectOutputStream out = null;
        try {
            out = new ObjectOutputStream(file);
            out.writeObject(e);
            out.close();
            file.close();
        } catch (IOException ex) {
            Logger.getLogger(ObjectCreator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        System.out.println("Serializing every game element");
        ObjectCreator.writeElement(Bomberman.getInstance(), "bomberman.dat");
        ObjectCreator.writeElement(new UndestroyableWall(), "indestructible_wall.dat");
        ObjectCreator.writeElement(new DestroyableWall(0), "destructible_wall.dat");
        ObjectCreator.writeElement(new DestroyableWall(1), "wall_with_bomb.dat");
        ObjectCreator.writeElement(new DestroyableWall(2), "wall_with_fire.dat");
        ObjectCreator.writeElement(new DestroyableWall(3), "wall_with_door.dat");
        ObjectCreator.writeElement(new DestroyableWall(4), "wall_with_speedup.dat");
        ObjectCreator.writeElement(new Bat(), "bat.dat");
        ObjectCreator.writeElement(new Radio(), "radio.dat");
    }
}
