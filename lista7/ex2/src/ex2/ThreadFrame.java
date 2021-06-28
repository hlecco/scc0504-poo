package ex2;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.WindowEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;


public class ThreadFrame extends Thread {
    
    private final JFrame jfTela;
    private final JLabel jlTexto;
    private boolean exit; // modo seguro de fechar a thread é colocando uma flag
    private int counter; // contador que será impresso na tela
    
    ThreadFrame() {
        jfTela = new JFrame();
        jfTela.setSize(new Dimension(200, 200));
        jfTela.setLayout(new BorderLayout());
        jfTela.setVisible(true);
        jlTexto = new JLabel("", SwingConstants.CENTER);
        jfTela.add(jlTexto);
        exit = false;
        counter = 0;
    }
    
    @Override
    public void run() {
        while (!exit) {
            jlTexto.setText(String.valueOf(counter));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ThreadFrame.class.getName()).log(Level.SEVERE, null, ex);
            }
            counter++;
        }
        jfTela.dispatchEvent(new WindowEvent(jfTela, WindowEvent.WINDOW_CLOSING));
    }
    
    public void stopThread() {
        exit = true;
    }
    
    public void bold() {
        jlTexto.setFont(jlTexto.getFont().deriveFont(jlTexto.getFont().getStyle() ^ Font.BOLD));
    }
    
    public void reset() {
        counter = 0;
    }
    
}
