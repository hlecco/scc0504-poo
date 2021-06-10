package exerc7;

import java.io.File;
import java.util.Comparator;

// classe usada para organizar o ArrayList com os arquivos pelo tamanho
public class MySort implements Comparator<File> {
    
    @Override
    public int compare(File f1, File f2){
        return (int) (f2.length() - f1.length());
    }
}
