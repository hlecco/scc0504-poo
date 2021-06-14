package Controler;

import Modelo.BomberUp;
import Modelo.Caveira;
import Modelo.Elemento;
import Modelo.Hero;
import Modelo.MuroDestrutivel;
import Modelo.MuroIndestrutivel;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class FaseReader {
    private Map<Character, Class> objDict;
    
    public void read(String filename, Tela t) throws FileNotFoundException, IOException {
        File file = new File(filename);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        
        int x = 0;
        int y = 0;
        
        String line = null;
        Elemento obj = null;
        while ((line = buffer.readLine()) != null) {
            for (y = 0; y < line.length(); y++) {
                switch (line.charAt(y)) {
                    case 'h':
                        obj = new Hero();
                        obj.setPosicao(x, y);
                        t.addElemento(obj);
                        break;
                    case 'c':
                        obj = new Caveira();
                        obj.setPosicao(x, y);
                        t.addElemento(obj);
                        break;
                    case 'w':
                        obj = new MuroDestrutivel(0);
                        obj.setPosicao(x, y);
                        t.addElemento(obj);
                        break;
                    case 'W':
                        obj = new MuroIndestrutivel();
                        obj.setPosicao(x, y);
                        t.addElemento(obj);
                        break;
                    case 'b':
                        obj = new MuroDestrutivel(1);
                        obj.setPosicao(x, y);
                        t.addElemento(obj);
                        break;
                    case 'f':
                        obj = new MuroDestrutivel(2);
                        obj.setPosicao(x, y);
                        t.addElemento(obj);
                        break;
                    case 'd':
                        obj = new MuroDestrutivel(3);
                        obj.setPosicao(x, y);
                        t.addElemento(obj);
                        break;
                }
            }
            x++;
        }
    }
}
