package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenhador;
import Auxiliar.Posicao;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class Sprite {
    private String filename;
    private Image spriteSheet;
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
    
    public Sprite(String filename, int hSize, int vSize, int nFrames, int hOffset, int vOffset) {
        this.filename = "";
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
    
    public void setFrame(int frame) {
        if (frame >= this.nFrames) {
            return;
        }
        
        BufferedImage bi = new BufferedImage(
            Consts.CELL_SIDE*this.hSize,
            Consts.CELL_SIDE*this.vSize,
            BufferedImage.TYPE_INT_ARGB
         );
         Graphics g = bi.createGraphics();
         g.drawImage(
            this.spriteSheet,
            0, 0, Consts.CELL_SIDE*this.hSize, Consts.CELL_SIDE*this.vSize,
            this.frame*Consts.IMG_SIDE, 0, (this.frame+1)*Consts.IMG_SIDE, Consts.IMG_SIDE*this.vSize,
            null
         );
         this.thisFrame.setImage(bi);
         this.frame = frame;
    }
    
    public int getFrame() {
        return this.frame;
    }
    
    public void changeSheet(String filename) {
        if (filename.equals(this.filename)) {
            return;
        }
        try {
            spriteSheet = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH + filename).getImage();
            this.filename = filename;
        } catch (IOException ex) {
            Logger.getLogger(Sprite.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void cycle() {
        if (this.frame == this.nFrames-1) {
            this.setFrame(0);
        }
        else {
            this.setFrame(this.frame + 1);
        }
    }
    
    public void draw(int x, int y) {
        Desenhador.desenhar(this.thisFrame, x+this.hOffset, y+this.vOffset);
    }
}
