package Controler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Auxiliar.Consts;
import Auxiliar.Draw;
import Model.Bat;
import Model.Bomberman;
import Model.DestroyableWall;
import Model.Element;
import Model.Life;
import Model.Radio;
import Model.Transition;
import Model.UndestroyableWall;


/*
  - h: Bomberman
  - w: DestroyableWall
  - W: UndestroyableWall
  - b: DestroyableWall com bomba+
  - f: DestroyableWall com fogo+
  - d: DestroyableWall com door
  - s: DestroyableWall com speed+
  - B: Bat
  - R: Radio
 */
public class Fase {

    int numFase;

    public Fase(int pNumFase) {
        this.numFase = pNumFase;
    }

    public void print(int pNumLife, Screen s) throws FileNotFoundException, IOException {
        if (numFase < 1) {
            numFase = 1;
        } else if (numFase > Consts.FASES) {
            Transition obj = new Transition("theend.png");
            obj.setPosition(0, 0);
            obj.addClock(60, 1, null, obj::end, false);
            s.addElement(obj);
        }

        Element obj = new Transition("fase" + Integer.toString(numFase) + ".png");
        obj.setPosition(0, 0);
        obj.addClock(15, 1, null, obj::remove, false);
        s.addElement(obj);
        Draw.getScreen().setStage(this, numFase, pNumLife);
    }

    public void read(int pNumLife, Screen s) throws FileNotFoundException, IOException {
        if (numFase < 1) {
            numFase = 1;
        } else if (numFase > Consts.FASES) {
            numFase = Consts.FASES;
        }

        File file = new File("fases/fase" + Integer.toString(numFase) + ".txt");
        BufferedReader buffer = new BufferedReader(new FileReader(file));

        int x = 0, y = 0;
        String line = null;
        Element obj;

        while ((y < Consts.RES) && (line = buffer.readLine()) != null) {
            for (x = 0; (x < line.length()) && (x < Consts.RES); x++) {
                obj = null;
                switch (line.charAt(x)) {
                    case 'h':
                        obj = new Bomberman(numFase, pNumLife);
                        break;
                    case 'W':
                        obj = new UndestroyableWall();
                        break;
                    case 'w':
                        obj = new DestroyableWall(0);
                        break;
                    case 'b':
                        obj = new DestroyableWall(1);
                        break;
                    case 'f':
                        obj = new DestroyableWall(2);
                        break;
                    case 'd':
                        obj = new DestroyableWall(3);
                        break;
                    case 's':
                        obj = new DestroyableWall(4);
                        break;
                    case 'B':
                        obj = new Bat();
                        break;
                    case 'R':
                        obj = new Radio();
                        break;
                }
                if (obj != null) {
                    obj.setPosition(x, y);
                    s.addElement(obj);
                }
            }
            y++;
        }

        obj = new Life(pNumLife);
        obj.setPosition(x, y - 1);
        s.addElement(obj);
    }

}