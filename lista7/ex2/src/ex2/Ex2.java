package ex2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Ex2 {

    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        ArrayList<ThreadFrame> listThreads = new ArrayList();
        
        while(true) {
            try {
                input = br.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Ex2.class.getName()).log(Level.SEVERE, null, ex);
            }
            switch (input) {
                case "exit":
                    // fecha as threads antes só por garantia
                    for (ThreadFrame tfloop : listThreads) {
                        tfloop.stopThread();
                    }
                    System.exit(0);
                case "new":
                    ThreadFrame tf = new ThreadFrame();
                    tf.start();
                    listThreads.add(tf);
                    break;
                case "close":
                    for (ThreadFrame tfloop : listThreads) {
                        tfloop.stopThread(); // também fecha a tela
                    }
                    break;
                case "bold":
                    for (ThreadFrame tfloop : listThreads) {
                        tfloop.bold(); // na verdade já comeca com bold, então
                        // tem que digitar duas vezes pra voltar ao normal e
                        // depois ir pra bold de novo
                    }
                    break;
                case "reset":
                    for (ThreadFrame tfloop : listThreads) {
                        tfloop.reset(); // também fecha a tela
                    }
                    break;
                default:
                    break;
            }
        }
    }
    
}
