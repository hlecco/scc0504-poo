package Clocks;

import Model.Bomberman;
import Model.SerializableRunnable;

public class Recharge implements SerializableRunnable {

    @Override
    public void run() {
        Bomberman.getInstance().recharge();
    }
    
}
