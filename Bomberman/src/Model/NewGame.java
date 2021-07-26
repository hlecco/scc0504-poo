package Model;

import Auxiliar.Draw;
import Controller.SaveAndLoad;

public class NewGame extends Selector {

    public NewGame(boolean state) {
        super("new game", state);
    }

    public void run() {
        Draw.getScreen().startFirstStage();
        SaveAndLoad.getInstance().setActive(true);
    }
}
