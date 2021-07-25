package Clocks;

import Auxiliar.Draw;
import Model.SerializableRunnable;

public class Close implements SerializableRunnable {

    @Override
    public void run() {
        Draw.getScreen().close();
    }
    
}
