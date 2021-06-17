package Modelo;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import Controler.Tela;


public class Main {

    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                Tela tTela = null;
                try {
                    tTela = new Tela("fase1");
                } catch (IOException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                tTela.setVisible(true);
                tTela.createBufferStrategy(2);
                tTela.go();
            }
        });
    }
}

