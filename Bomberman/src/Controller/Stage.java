package Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import Auxiliar.Consts;
import Model.AutosaveInterval;
import Model.Bomberman;
import Model.Continue;
import Model.Display;
import Model.NewGame;
import Model.SelectorController;
import Model.Text;


public class Stage {

    private static int numStage;

    public Stage() {
        this.numStage = 0;
    }

    public void setStage(int pNumFase) {
        this.numStage = pNumFase;
    }

    public int getStage() {
        return this.numStage;
    }
    
    public void next() {
        if (this.numStage < Consts.FASES + 2) {
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
        } else if (numStage > Consts.FASES) {
            this.end(s);
            return;
        }

        Text transition = new Text();
        transition.setPosition(Consts.RES/2-3, Consts.RES/2);
        transition.addClock(30, 1, null, transition::remove, false);
        s.addElement(transition);
        transition.write("stage" + Integer.toString(this.numStage));
        this.read(s);
        
        Display life_count = new Display();
        Bomberman.getInstance().addObserver(life_count);
        s.addElement(life_count);
        life_count.setPosition(0, Consts.RES-1);
    }

    /*
    Lê um arquivo que contém a fase e coloca os elementos na tela. Os elementos
    da fase são colocados de acordo com a tabela acima.
    
    Parâmetros: número de vidas do Bomberman e a tela.
    A tela serve para colocar os elementos nela e o número de vidas é necessário
    para colocar a imagem com o número de vidas restantes do Bomberman corretamente.
     */
    public synchronized void read(Screen s) throws FileNotFoundException, IOException {
        if (numStage < 1) {
            numStage = 1;
        } else if (numStage > Consts.FASES) {
            numStage = Consts.FASES;
        }

        File file = new File("stages/stage" + Integer.toString(numStage) + ".txt");
        BufferedReader buffer = new BufferedReader(new FileReader(file));

        int x = 0, y = 0;
        String line = null;
        ObjectCreator creator = new ObjectCreator(s);

        while ((y < Consts.RES) && (line = buffer.readLine()) != null) {
            for (x = 0; (x < line.length()) && (x < Consts.RES); x++) {
                creator.putObject(line.charAt(x), x, y);
            }
            y++;
        }
    }

    static void start(Screen s) {
        SelectorController controller = new SelectorController();
        s.addElement(controller);
        controller.addSelector(new NewGame(true));
        controller.addSelector(new Continue(false));
        controller.addSelector(new AutosaveInterval(false));
    }
    
    static void end(Screen s) {
        SaveAndLoad.getInstance().setActive(false);
        
        s.setBackgroundImage("blank.png");
        
        Text text1 = new Text();
        Text text2 = new Text();
        Text text3 = new Text();
        
        text1.setPosition(1, 1);
        text2.setPosition(1, 3);
        text3.setPosition(1, 4);
        
        s.addElement(text1);
        s.addElement(text3);
        s.addElement(text2);
        
        text1.write("the end");
        text2.write("caio 9390301");
        text3.write("lecco 10295822");
    }
}
