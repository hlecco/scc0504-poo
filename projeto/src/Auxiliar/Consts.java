package Auxiliar;

import java.awt.event.KeyEvent;
import java.io.File;

public class Consts {
    public static final int UP = KeyEvent.VK_UP;
    public static final int DOWN = KeyEvent.VK_DOWN;
    public static final int LEFT = KeyEvent.VK_LEFT;
    public static final int RIGHT = KeyEvent.VK_RIGHT;
    public static final int BOMB = KeyEvent.VK_X;
    public static final int DOOR = KeyEvent.VK_Z;
    public static final int CELL_SIDE = 32;
    public static final int RES = 33;
    public static final int FRAME_INTERVAL = 100;   
    public static final int TIMER_DISPARO = 20; /*Em numero de frames (redesenhos)*/
    public static final int MAX_BOMBS = 8;
    public static final int MAX_POWER = 8;
    public static final String PATH = File.separator + "imgs" + File.separator;
}
