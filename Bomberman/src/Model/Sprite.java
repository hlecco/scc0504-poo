package Model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

import Auxiliar.Consts;
import Auxiliar.Draw;
import java.io.ObjectInputStream;
import java.io.Serializable;

/*
Classe para abstrair o desenho dos elementos na tela. Responsável por definir o
tamanho e as "animações" dos elementos.
 */
public class Sprite implements Serializable {

    private String filename;
    transient private Image spriteSheet;
    private ImageIcon thisFrame;
    private int frame;
    private int nFrames;
    private int hSize;
    private int vSize;
    private int hOffset;
    private int vOffset;

    public Sprite(String filename) {
        this(filename, 1, 1, 1, 0, 0);
    }

    public Sprite(String filename, int hSize, int vSize) {
        this(filename, hSize, vSize, 1, 0, 0);
    }

    public Sprite(String filename, int hSize, int vSize, int nFrames) {
        this(filename, hSize, vSize, nFrames, 0, 0);
    }

    public Sprite(String filename, int hSize, int vSize, int hOffset, int vOffset) {
        this(filename, hSize, vSize, 1, hOffset, vOffset);
    }

    public Sprite(String filename, int hSize, int vSize, int nFrames,
            int hOffset, int vOffset) {
        this.filename = filename;
        this.thisFrame = new ImageIcon();
        this.hSize = hSize;
        this.vSize = vSize;
        this.nFrames = nFrames;
        this.frame = 0;
        this.vOffset = vOffset;
        this.hOffset = hOffset;
        this.changeSheet(filename);
        this.setFrame(this.frame);
    }

    /*
    Define o frame da animação.
     */
    public void setFrame(int frame) {
        if (frame >= this.nFrames) {
            return;
        }

        BufferedImage bi = new BufferedImage(Consts.CELL_SIDE * this.hSize,
                Consts.CELL_SIDE * this.vSize,
                BufferedImage.TYPE_INT_ARGB);
        Graphics g = bi.createGraphics();
        g.drawImage(this.spriteSheet, 0, 0,
                Consts.CELL_SIDE * this.hSize,
                Consts.CELL_SIDE * this.vSize,
                this.frame * Consts.IMG_SIDE * this.hSize,
                0, (this.frame + 1) * Consts.IMG_SIDE * this.hSize,
                Consts.IMG_SIDE * this.vSize, null);
        this.thisFrame.setImage(bi);
        this.frame = frame;
    }

    public int getFrame() {
        return this.frame;
    }

    public String getStrSprite() {
        return this.filename + " "
                + Integer.toString(this.hSize) + " "
                + Integer.toString(this.vSize) + " "
                + Integer.toString(this.nFrames) + " "
                + Integer.toString(this.hOffset) + " "
                + Integer.toString(this.vOffset);
    }

    /*
    Troca a imagem do elemento.
     */
    public void changeSheet(String filename) {
        this.filename = filename;

        try {
            spriteSheet = new ImageIcon(new java.io.File(".").getCanonicalPath()
                    + Consts.PATH + filename).getImage();
            this.filename = filename;
        } catch (IOException ex) {
            Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setFrame(this.frame);
    }

    /*
    Vai para o próximo frame da animação.
     */
    public void cycle() {
        if (this.frame == this.nFrames - 1) {
            this.setFrame(0);
        } else {
            this.setFrame(this.frame + 1);
        }
    }

    public int getNFrames() {
        return this.nFrames;
    }

    public void draw(int x, int y) {
        Draw.Draw(this.thisFrame, x + this.hOffset, y + this.vOffset);
    }
    
    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        this.changeSheet(this.filename);
    }
}
