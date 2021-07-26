package Model;

import java.io.Serializable;

public abstract class Selector extends Text implements Serializable, Runnable {
    // Class for menus and settings
    // Override run method to do something when pressed

    private boolean state;
    protected String selectorText;

    public Selector(String text, boolean state) {
        this.priority = 10;
        this.state = state;
        this.selectorText = text;
    }

    public void cycle() {
        state = !state;
        this.showText();
    }

    public boolean isSelected() {
        return state;
    }

    public void showText() {
        if (this.isSelected()) {
            this.write(">" + this.selectorText);
        } else {
            this.write(this.selectorText);
        }
    }
}
