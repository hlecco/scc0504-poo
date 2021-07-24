package Model;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Controler.Screen;


public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    Screen screen = new Screen();
                    screen.startGame();
                    screen.setVisible(true);
                    screen.createBufferStrategy(2);
                    screen.go();
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
    
}