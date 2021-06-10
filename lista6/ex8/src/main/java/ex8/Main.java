package ex8;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void main(String[] args) {
        File file = new File("texto.txt");
        BufferedReader buffer = null;
        try {
            buffer = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        var lineNumber = new MeuLineNumberReader(buffer);
        
        String line = null;
        while ((line=lineNumber.readLine())!=null) {
            System.out.println(line);
        }
    }
}
