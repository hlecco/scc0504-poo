package Clocks;

import Auxiliar.Draw;
import Model.SerializableRunnable;

public class ResetStage implements SerializableRunnable {

    @Override
    public void run() {
        Draw.getScreen().resetStage();
    }
    
}
