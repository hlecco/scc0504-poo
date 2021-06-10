package ex5;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        DadosPessoais data = new DadosPessoais("Rua João da Cruz, 94, Pirassununga, SP",
                                               "Carlos", "85844581075");
        try {
            data.write_file("data.gz");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        DadosPessoais newdata = null;
        try {
            newdata = DadosPessoais.read_file("data.gz");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        newdata.show();
    }
}
