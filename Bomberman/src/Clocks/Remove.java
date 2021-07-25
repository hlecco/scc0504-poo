package Clocks;

import Auxiliar.Draw;
import Model.Element;
import Model.SerializableRunnable;

public class Remove implements SerializableRunnable {

    private final Element toBeRemoved;

    public Remove(Element e) {
        this.toBeRemoved = e;
    }

    @Override
    public void run() {
        Draw.getScreen().removeElement(this.toBeRemoved);
    }

}
