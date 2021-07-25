package Clocks;

import Auxiliar.Draw;
import Model.SerializableRunnable;

public class NextStage implements SerializableRunnable {

    @Override
    public void run() {
        Draw.getScreen().nextStage();
    }

}
