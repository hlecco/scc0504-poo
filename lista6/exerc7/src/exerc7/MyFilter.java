package exerc7;

import java.io.File;
import java.io.FilenameFilter;

// classe usada para filtrar os objetos do tipo mp3
public class MyFilter implements FilenameFilter {
    private final String ext;
    
    MyFilter(String extension) {
        ext = extension;
    }
    
    @Override
    public boolean accept(File file, String name) {
        return file.isFile() && name.endsWith(ext);
        // a verificação de ser um arquivo é redundante porque na main chamamos
        // apenas para arquivos, mas é necessária para fazer overriding
    }
}