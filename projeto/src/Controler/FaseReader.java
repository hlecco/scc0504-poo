package Controler;

import Auxiliar.Consts;
import Modelo.Bat;
import Modelo.BomberUp;
import Modelo.Elemento;
import Modelo.Hero;
import Modelo.MuroDestrutivel;
import Modelo.MuroIndestrutivel;
import Modelo.Radio;
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
  - s: MuroDestrutivel com speed+
  - B: Bat
  - R: Radio
*/


public class FaseReader {
    private Map<Character, Class> objDict;
    
    public int read(int numFase, Tela t) throws FileNotFoundException, IOException {
        if (numFase < 1) {
            numFase = 1;
        }
        else if (numFase > Consts.FASES) {
            numFase = Consts.FASES;
        }
        File file = new File("fases/fase"+Integer.toString(numFase)+".txt");
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        
        int x = 0;
        int y = 0;
        
        String line = null;
        Elemento obj = null;
        
        obj = new Transicao("fase"+Integer.toString(numFase)+".png");
        obj.setPosicao(x, y);
        t.addElemento(obj);
        
        
        while ((y < Consts.RES) & (line = buffer.readLine()) != null) {
            for (x = 0; (x < line.length()) & (x < Consts.RES); x++) {
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
                    case 's':
                        obj = new MuroDestrutivel(4);
                        break;
                    case 'B':
                        obj = new Bat();
                        break;
                     case 'R':
                        obj = new Radio();
                        break;
                }
                if (obj != null) {
                    obj.setPosicao(x, y);
                    t.addElemento(obj);
                }
            }
            y++;
        }
        return numFase;
    }
}
