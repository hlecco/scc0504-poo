package ex8;

import java.io.BufferedReader;
import java.io.IOException;

public class MeuLineNumberReader {
    private BufferedReader buffer;
    private int line;

    MeuLineNumberReader(BufferedReader buf) {
        line = 0;
        buffer = buf;
    }
    
    public String readLine() throws IOException {
        String lineRead = buffer.readLine();
        if (lineRead == null) return null;
        
        String str = String.valueOf(line)
                     + ": " + lineRead;
        line++;
        return str;
    }
    
    public void close() throws IOException {
        buffer.close();
    }
}
