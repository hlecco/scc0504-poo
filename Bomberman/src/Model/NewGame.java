package Model;

import Auxiliar.Draw;
import Controller.SaveAndLoad;

public class NewGame extends Selector {

    public NewGame(boolean state) {
        super("newgame.png", state);
    }

    public void run() {
        //SaveAndLoad.getInstance().start();
        Draw.getScreen().startFirstStage();
    }
}
