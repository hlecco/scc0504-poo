package ex6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public static void replace_in_file(String path, String old_str, String new_str) throws FileNotFoundException, IOException {
        var file = new File(path);
        var reader = new BufferedReader(new FileReader(file));
        
        var newFile = new File(path+".tmp");
        var writer = new BufferedWriter(new FileWriter(newFile));
        
        String line = null;
        String newLine = null;
        while ((line=reader.readLine())!=null) {
            newLine = line.replaceAll(old_str, new_str);
            writer.write(newLine);
            writer.newLine();
        }
        writer.close();
        newFile.renameTo(file);
    }
    
    public static void main(String[] args) {
        try {
            replace_in_file("texto.txt", "muito", "pouco");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
