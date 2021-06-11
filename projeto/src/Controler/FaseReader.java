/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controler;

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

/**
 *
 * @author lecco
 */
public class FaseReader {
    private Map<Character, Class> objDict;
    
    public FaseReader() {
        //objDict = Map<Character, Class>;
        //objDict.put('h', Hero.class);
        //objDict.put('c', Caveira.class);
        //objDict.put('w', MuroDestrutivel.class);
        //objDict.put('W', MuroIndestrutivel.class);
        //objDict.put(' ', null);
    }
    
    public void read(String filename, Tela t) throws FileNotFoundException, IOException {
        File file = new File(filename);
        BufferedReader buffer = new BufferedReader(new FileReader(file));
        
        int x = 0;
        int y = 0;
        
        String line = null;
        Elemento obj = null;
        while ((line = buffer.readLine()) != null) {
            for (x = 0; x < line.length(); x++) {
                switch (line.charAt(x)) {
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
                        obj = new MuroDestrutivel();
                        obj.setPosicao(x, y);
                        t.addElemento(obj);
                        break;
                    case 'W':
                        obj = new MuroIndestrutivel();
                        obj.setPosicao(x, y);
                        t.addElemento(obj);
                        break;
                }
            }
            y++;
        }
    }
}
