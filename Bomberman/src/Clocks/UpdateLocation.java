package Clocks;

import Auxiliar.Draw;
import Model.Bomberman;
import Model.SerializableRunnable;

public class UpdateLocation implements SerializableRunnable {

    /*
    Método usado para que os inimigos saibam a posição do Bomberman na tela.
     */
    @Override
    public void run() {
        Draw.getScreen().setBombermanPosition(Bomberman.getInstance().getPosition());
    }

}
