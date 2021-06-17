package Controler;

import Modelo.Bat;
import Modelo.BomberUp;
import Modelo.Caveira;
import Modelo.Elemento;
import Modelo.Hero;
import Modelo.MuroDestrutivel;
import Modelo.MuroIndestrutivel;
import Modelo.Transicao;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
  - h: Hero
  - w: MuroDestrutivel
  - W: MuroIndestrutivel
  - b: MuroDestrutivel com bomba+
  - f: MuroDestrutivel com fogo+
  - d: MuroDestrutivel com door
  - B: Bat
*/


public class FaseReader {
    private Map<Character, Class> objDict;
    
    public void read(String filename, Tela t) throws FileNotFoundException, IOException {
        File file = new File(filename+".txt");
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        
        int x = 0;
        int y = 0;
        
        String line = null;
        Elemento obj = null;
        
        obj = new Transicao(filename+".png");
        obj.setPosicao(x, y);
        t.addElemento(obj);
        
        
        while ((line = buffer.readLine()) != null) {
            for (x = 0; x < line.length(); x++) {
                obj = null;
                switch (line.charAt(x)) {
                    case 'h':
                        obj = new Hero();
                        break;
                    case 'w':
                        obj = new MuroDestrutivel(0);
                        break;
                    case 'W':
                        obj = new MuroIndestrutivel();
                        break;
                    case 'b':
                        obj = new MuroDestrutivel(1);
                        break;
                    case 'f':
                        obj = new MuroDestrutivel(2);
                        break;
                    case 'd':
                        obj = new MuroDestrutivel(3);
                        break;
                    case 'B':
                        obj = new Bat();
                        break;
                }
                if (obj != null) {
                    obj.setPosicao(x, y);
                    t.addElemento(obj);
                }
            }
            y++;
        }
    }
}
