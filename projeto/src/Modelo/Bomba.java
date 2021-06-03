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
    
    public void explode() {
        Fogo fogo_up = new Fogo("fogo.png");
        Fogo fogo_down = new Fogo("fogo.png");
        Fogo fogo_left = new Fogo("fogo.png");
        Fogo fogo_right = new Fogo("fogo.png");
        
        fogo_up.setPotencia(this.potencia-1);
        fogo_down.setPotencia(this.potencia-1);
        fogo_left.setPotencia(this.potencia-1);
        fogo_right.setPotencia(this.potencia-1);
        
        fogo_up.setDirecao(Consts.UP);
        fogo_down.setDirecao(Consts.DOWN);
        fogo_left.setDirecao(Consts.LEFT);
        fogo_right.setDirecao(Consts.RIGHT);
        
        fogo_up.setPosicao(this.pPosicao.getLinha(), this.pPosicao.getColuna()-1);
        fogo_down.setPosicao(this.pPosicao.getLinha(), this.pPosicao.getColuna()+1);
        fogo_left.setPosicao(this.pPosicao.getLinha()-1, this.pPosicao.getColuna());
        fogo_right.setPosicao(this.pPosicao.getLinha()+1, this.pPosicao.getColuna());
        
        Desenhador.getTelaDoJogo().addElemento(fogo_up);
        Desenhador.getTelaDoJogo().addElemento(fogo_left);
        Desenhador.getTelaDoJogo().addElemento(fogo_right);
        Desenhador.getTelaDoJogo().addElemento(fogo_down);
        
        fogo_up.propaga();
        fogo_left.propaga();
        fogo_right.propaga();
        fogo_down.propaga();
        
        Desenhador.getTelaDoJogo().removeElemento(this);
    }
}
