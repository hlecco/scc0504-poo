package Modelo;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;

import Auxiliar.Consts;
import Auxiliar.Desenhador;
import Controler.Tela;
import Auxiliar.Posicao;
import Controler.ControleDeJogo;


public abstract class Elemento implements Serializable {
    protected Tela pTela;
    protected ImageIcon iImage;
    protected Posicao pPosicao;
    protected boolean bTransponivel; // Pode passar por cima?
    protected boolean bMortal; // Se encostar, morre?
    protected boolean bDestrutivel; // Destrutível com fogo
    protected int hidden; // 0: nada escondido, 1: bomberup, 2: fireup, 3: fogo, 4: porta
       
    protected Elemento(String sNomeImagePNG) {
        this.pPosicao = new Posicao(1, 1);
        this.bTransponivel = true;
        this.bMortal = false;
        this.hidden = 0;
        
        try {
            iImage = new ImageIcon(new java.io.File(".").getCanonicalPath() + Consts.PATH + sNomeImagePNG);
            Image img = iImage.getImage();
            BufferedImage bi = new BufferedImage(Consts.CELL_SIDE, Consts.CELL_SIDE, BufferedImage.TYPE_INT_ARGB);
            Graphics g = bi.createGraphics();
            g.drawImage(img, 0, 0, Consts.CELL_SIDE, Consts.CELL_SIDE, null);
            iImage = new ImageIcon(bi);
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
    
    protected void destroiElemento() {
        Desenhador.getTelaDoJogo().removeElemento(this);
    }
    
    public void Event(int key, ControleDeJogo c, Tela t) {}

    public Posicao getPosicao() {
        return pPosicao;
    }

    public boolean isbTransponivel() {
        return bTransponivel;
    }

    public void setbTransponivel(boolean bTransponivel) {
        this.bTransponivel = bTransponivel;
    }
    
    public boolean isbDestrutivel() {
        return bDestrutivel;
    }
    
    public void setbDestrutivel(boolean pDest) {
        this.bDestrutivel = pDest;
    }

    public boolean setPosicao(int coluna, int linha) {
        boolean test = pPosicao.setPosicao(coluna, linha);
        Desenhador.getTelaDoJogo().moveElemento(this);
        for (Elemento e: Desenhador.getTelaDoJogo().buscaElemento(this.getPosicao())) {
            this.touchAnother(e);
        }
        return test;
    }
    
    public int getHiddenItem() {
        return this.hidden;
    }
    
    public void setHidden(int pHidden) {
        this.hidden = pHidden;
    }

    public boolean moveUp() {
        boolean test = this.pPosicao.moveUp();
        Desenhador.getTelaDoJogo().moveElemento(this);
        for (Elemento e: Desenhador.getTelaDoJogo().buscaElemento(this.getPosicao())) {
            this.touchAnother(e);
        }
        return test;
    }

    public boolean moveDown() {
        boolean test = this.pPosicao.moveDown();
        Desenhador.getTelaDoJogo().moveElemento(this);
        for (Elemento e: Desenhador.getTelaDoJogo().buscaElemento(this.getPosicao())) {
            this.touchAnother(e);
        }
        return test;
    }

    public boolean moveRight() {
        boolean test = this.pPosicao.moveRight();
        Desenhador.getTelaDoJogo().moveElemento(this);
        for (Elemento e: Desenhador.getTelaDoJogo().buscaElemento(this.getPosicao())) {
            this.touchAnother(e);
        }
        return test;
    }

    public boolean moveLeft() {
        boolean test = this.pPosicao.moveLeft();
        Desenhador.getTelaDoJogo().moveElemento(this);
        for (Elemento e: Desenhador.getTelaDoJogo().buscaElemento(this.getPosicao())) {
            this.touchAnother(e);
        }
        return test;
    }
    
   public void autoDesenho() {
        Desenhador.desenhar(this.iImage, pPosicao.getColuna(), pPosicao.getLinha());        
    }
   
   protected void touchFire() {} // o que acontece quando o elemento toca o fogo
   protected void touchHero(Hero h) {} // o que acontece quando o elemento toca o heroi
   protected void touchAnother(Elemento e) {} // o que acontece com o elemento que esse objeto toca
}