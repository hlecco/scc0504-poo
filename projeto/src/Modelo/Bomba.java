package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenhador;
import Auxiliar.Posicao;
import Controler.ControleDeJogo;
import Controler.Tela;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


public class Bomba extends Elemento {

    private int potencia;

    public Bomba(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.potencia = 1;
        this.bTransponivel = false;
    }
    
    public void setPotencia(int p) {
        if (p > 0) {
            this.potencia = p;
        }
    }
    
    public void explode(ControleDeJogo c, Tela t) {
        Posicao offset = new Posicao(pPosicao.getLinha(), pPosicao.getColuna());
        
        offset.moveUp();
        if (c.ehPosicaoValida(t.getElementos(), offset)) {
            Fogo fogo_up = new Fogo("fogo.png"); 
            fogo_up.setPotencia(this.potencia);
            fogo_up.setDirecao(Consts.UP);
            fogo_up.setPosicao(offset.getLinha(), offset.getColuna());
            fogo_up.propaga(c, t);
        }
        
        offset.volta();
        offset.moveDown();
        if (c.ehPosicaoValida(t.getElementos(), offset)) {
            Fogo fogo_down = new Fogo("fogo.png");
            fogo_down.setPotencia(this.potencia);
            fogo_down.setDirecao(Consts.DOWN);
            fogo_down.setPosicao(offset.getLinha(), offset.getColuna());
            fogo_down.propaga(c, t);
        }
        
        offset.volta();
        offset.moveLeft();
        if (c.ehPosicaoValida(t.getElementos(), offset)) {
            Fogo fogo_left = new Fogo("fogo.png");
            fogo_left.setPotencia(this.potencia);
            fogo_left.setDirecao(Consts.LEFT);
            fogo_left.setPosicao(offset.getLinha(), offset.getColuna());
            fogo_left.propaga(c, t);
        }
        
        offset.volta();
        offset.moveRight();
        if (c.ehPosicaoValida(t.getElementos(), offset)) {
            Fogo fogo_right = new Fogo("fogo.png");
            fogo_right.setPotencia(this.potencia);
            fogo_right.setDirecao(Consts.RIGHT);
            fogo_right.setPosicao(offset.getLinha(), offset.getColuna());
            fogo_right.propaga(c, t);
        }
        
        Desenhador.getTelaDoJogo().removeElemento(this);
    }
}
