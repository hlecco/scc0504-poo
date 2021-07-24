package Model;

import Auxiliar.Draw;


public class NewGame extends Selector{
    public NewGame(boolean state) {
        super("newgame.png", state);
    }
    
    public void run() {
        Draw.getScreen().startFirstStage();
    }
}
