package exerc7;

import java.io.File;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Exerc7 {

    public static void main(String[] args) {
        String extension = "mp3";
        String dirPath;
        try (Scanner scanner = new Scanner(System.in)) {
            dirPath = scanner.next();
        }
        // Opção hardcoded: estava usando a própria pasta de teste que o
        // netbeans cria para renomear os arquivos.
        // String dirPath = "." + File.separator + "test";
        renameAll(dirPath, extension);
    }
    
    public static void renameAll(String dirPath, String extension) {
        File source = new File(dirPath);
        MyFilter filter = new MyFilter(extension); // objeto que serve apenas para
        // chamarmos o método filter para filtrar os arquivos mp3
        int count = 0;
        if (source.isDirectory()) {
            List<File> files = Arrays.asList(source.listFiles()); // converte array pra ArrayList para poder ordenar
            Collections.sort(files, new MySort()); // o segundo parâmetro é o que indica que irá ordenar pelo tamanho
            for (File file : files) {
                if (!file.isDirectory() && filter.accept(file, extension)) {
                    count++;
                    String newname = renameFile(file.getName());
                    newname = String.format("%0" + 4 + "d", count) + "." + newname;
                    newname = file.getParent() + File.separator + newname;
                    File newfile = new File(newname);
                    file.renameTo(newfile);
                }
            }
        }
    }
    
    public static String renameFile(String name) {
        String newname = "";
        for (int i = 0; i < name.length() - 4; i++) {
            if (name.charAt(i) != '-' && !Character.isDigit(name.charAt(i))) {
                newname += name.charAt(i);
            }
        }
        return newname + ".mp3";
    }
}