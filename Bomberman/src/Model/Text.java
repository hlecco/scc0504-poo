package Model;

import Auxiliar.Draw;
import Controller.Screen;
import java.util.ArrayList;

public class Text extends Element {
    private ArrayList<Digit> text;
    public Text() {
        super("blank.png");
        text = new ArrayList<Digit>();
    }
    
    @SuppressWarnings("unchecked")
    public void write(String str) {
        Screen s = Draw.getScreen();
        for (Digit d: (ArrayList<Digit>) text.clone()) {
            text.remove(d);
            d.remove();
        }
        boolean special = false;
        for (int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if (c == '$') {
                special = true;
            }
            else {
                this.addLetter(str.charAt(i), special, s);
                special = false;
            }
        }
    }
    
    private void addLetter(char c, boolean special, Screen s) {
        Digit newLetter = new Digit(c, special);
        newLetter.setPosition(this.getPosition().getCol() + this.text.size(), this.getPosition().getRow());
        this.text.add(newLetter);
        s.addElement(newLetter);
    }
    
    @Override
    @SuppressWarnings("unchecked")
    public void remove() {
        for (Digit d: (ArrayList<Digit>) text.clone()) {
            text.remove(d);
            d.remove();
        }
        super.remove();
    }
}
