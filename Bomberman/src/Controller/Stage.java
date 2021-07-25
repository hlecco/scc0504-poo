package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Auxiliar.Consts;
import Auxiliar.Draw;
import Clocks.Close;
import Clocks.Remove;
import Model.Bat;
import Model.Bomberman;
import Model.Continue;
import Model.DestroyableWall;
import Model.Element;
import Model.Life;
import Model.NewGame;
import Model.Radio;
import Model.SelectorController;
import Model.Transition;
import Model.UndestroyableWall;
import java.util.logging.Level;
import java.util.logging.Logger;


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
public class Stage {

    private int numStage;

    public Stage() {
        this.numStage = 0;
    }

    public void setStage(int pNumFase) {
        this.numStage = pNumFase;
    }

    public void next() {
        if (this.numStage < Consts.FASES + 1) {
            numStage++;
        }
    }

    /*
    Coloca a imagem de transição de fases e a imagem final na tela. Em seguida
    chama a função que colocará os objetos na tela.
    
    Parâmetros: número de vidas do Bomberman e a tela.
    A tela serve para colocar a imagem nela e o número de vidas é necessário
    para passar pra função read.
     */
    public void print(Screen s) throws FileNotFoundException, IOException {
        if (numStage < 1) {
            numStage = 1;
        } else if (numStage >= Consts.FASES) {
            Transition obj = new Transition("theend.png");
            Close c = new Close();
            obj.setPosition(0, 0);
            obj.addClock(60, 1, null, c::run, false);
            s.addElement(obj);
        }

        Transition obj = new Transition("stage" + Integer.toString(numStage) + ".png");
        obj.setPosition(0, 0);
        Remove r = new Remove(obj);
        obj.addClock(15, 1, null, r::run, false);
        //SaveAndLoad.getInstance().terminate();
        s.addElement(obj);
        //Thread.sleep(3000);
        //Draw.getScreen().removeElement(obj);
        this.read(s);
    }

    /*
    Lê um arquivo que contém a fase e coloca os elementos na tela. Os elementos
    da fase são colocados de acordo com a tabela acima.
    
    Parâmetros: número de vidas do Bomberman e a tela.
    A tela serve para colocar os elementos nela e o número de vidas é necessário
    para colocar a imagem com o número de vidas restantes do Bomberman corretamente.
     */
    public void read(Screen s) throws FileNotFoundException, IOException {
        if (numStage < 1) {
            numStage = 1;
        } else if (numStage > Consts.FASES) {
            numStage = Consts.FASES;
        }

        File file = new File("stages/stage" + Integer.toString(numStage) + ".txt");
        BufferedReader buffer = new BufferedReader(new FileReader(file));

        int x = 0, y = 0;
        String line = null;
        Element obj;

        while ((y < Consts.RES) && (line = buffer.readLine()) != null) {
            for (x = 0; (x < line.length()) && (x < Consts.RES); x++) {
                obj = null;
                switch (line.charAt(x)) {
                    case 'h':
                        obj = Bomberman.getInstance();
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
        obj = new Life(Bomberman.getInstance().getNumLife());
        obj.setPosition(x, y - 1);
        s.addElement(obj);
        SaveAndLoad.getInstance().start();
    }

    static void start(Screen s) {
        SelectorController controller = new SelectorController();
        s.addElement(controller);
        controller.addSelector(new NewGame(true));
        controller.addSelector(new Continue(false));
    }
}
