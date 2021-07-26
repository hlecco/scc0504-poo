package Model;

public class Digit extends Element {
    // Prints a single digit on screen

    public Digit(String str) {
        this(str.charAt(0), false);
    }
    public Digit(char c) {
        this(c, false);
    }
    public Digit(String str, boolean special) {
        this(str.charAt(0), special);
    }
    public Digit(char c, boolean special) {
        super((special ? "digits/special/" : "digits/") + c + ".png");
        this.priority = 15;
    }
}
