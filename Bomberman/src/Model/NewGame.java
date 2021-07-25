package Model;

import Auxiliar.Draw;
import Controller.SaveAndLoad;

public class NewGame extends Selector {

    public NewGame(boolean state) {
        super("newgame.png", state);
    }

    public void run() {
        SaveAndLoad.getInstance().setActive(true);
        Draw.getScreen().startFirstStage();
    }
}
