package Auxiliar;

import java.awt.Graphics;
import java.io.Serializable;
import javax.swing.ImageIcon;

import Controler.Screen;

public class Draw implements Serializable {

    static Screen scenery;

    public static void Draw(ImageIcon iImage, int iCol, int iRow) {
        iImage.paintIcon(scenery, getGraphics(),
                iCol * Consts.CELL_SIDE, iRow * Consts.CELL_SIDE);
    }

    public static void setScene(Screen pScenery) {
        scenery = pScenery;
    }

    public static Screen getScreen() {
        return scenery;
    }

    /* private, usado apenas aqui dentro */
    private static Graphics getGraphics() {
        return scenery.getGraphicsBuffer();
    }

}
