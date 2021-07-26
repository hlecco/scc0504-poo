package Model;

import Auxiliar.Consts;
import Auxiliar.Draw;
import Controller.GameControl;
import Controller.Screen;
import java.util.ArrayList;

public class SelectorController extends Element {
    // Controls selectors.
    // Use method addSelector to include a menu option

    private ArrayList<Selector> selectors;

    public SelectorController() {
        super("invisible.png");
        this.selectors = new ArrayList<Selector>();
    }

    public void addSelector(Selector s) {
        this.selectors.add(s);
        s.setPosition(this.getPosition().getCol(), this.getPosition().getRow()+this.selectors.size());
        Draw.getScreen().addElement(s);
        s.showText();
    }

    private void cycle(boolean reverse) {
        int current = this.findCurrent();
        if (current != -1) {
            int other = -1;
            if (reverse) {
                other = this.findPrevious();
            } else {
                other = this.findNext();
            }
            if (other != -1) {
                this.selectors.get(current).cycle();
                this.selectors.get(other).cycle();
            }
        }
    }


    @Override
    public void Event(int key, GameControl c, Screen t) {
        switch (key) {
            case Consts.UP:
                this.cycle(true);
                break;
            case Consts.DOWN:
                this.cycle(false);
                break;
            case Consts.BOMB:
                this.selectors.get(this.findCurrent()).run();
        }
    }

    private int findNext() {
        int index = this.findCurrent();
        if (selectors.isEmpty()) {
            return -1;
        }
        if (index >= this.selectors.size() - 1) {
            return 0;
        } else {
            return index + 1;
        }
    }

    private int findPrevious() {
        int index = this.findCurrent();
        if (selectors.isEmpty()) {
            return -1;
        }
        if (index <= 0) {
            return this.selectors.size() - 1;
        } else {
            return index - 1;
        }
    }

    private int findCurrent() {
        for (int i = 0; i < this.selectors.size(); i++) {
            if (this.selectors.get(i).isSelected()) {
                return i;
            }
        }
        return -1;
    }
}
