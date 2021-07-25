package Clocks;

import Auxiliar.Draw;
import Model.SerializableRunnable;

public class ResetGame implements SerializableRunnable {

    @Override
    public void run() {
        Draw.getScreen().resetGame();
    }
    
}
