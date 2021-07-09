package ex10;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Ex10 {

    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        ObservadorMaiuscula oM = new ObservadorMaiuscula();
        ObservadorAno oA = new ObservadorAno();
        ObservadorTamanho oT = new ObservadorTamanho();
        Observavel ob = new Observavel();
        
        ob.addObserver(oM);
        ob.addObserver(oA);
        ob.addObserver(oT);
        
        while(true) {
            try {
                input = br.readLine();
            } catch (IOException ex) {
                Logger.getLogger(Ex10.class.getName()).log(Level.SEVERE, null, ex);
            }
            if ("exit".equals(input)) {
                System.exit(0);
            } else {
                ob.setTexto(input);
            }
        }
        
    }
    
}
