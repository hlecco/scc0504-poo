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


public class Fogo extends Elemento {

    private int potencia;
    private int direcao;

    public Fogo(String sNomeImagePNG) {
        super(sNomeImagePNG);
        this.potencia = 1;
        this.direcao = Consts.DOWN;
    }
    
    public void setPotencia(int p) {
        if (p > -1) {
            this.potencia = p;
        }
    }
    
    public void setDirecao(int d) {
        this.direcao = d;
    }
    
    public void propaga() {
        int offset_linha = 0;
        int offset_coluna = 0;
        switch (this.direcao) {
            case Consts.UP:
                offset_coluna = -1;
                break;
            case Consts.DOWN:
                offset_coluna = 1;
                break;
            case Consts.LEFT:
                offset_linha = -1;
                break;
            case Consts.RIGHT:
                offset_linha = 1;
                break;
        }
        
        if (potencia > 0) {
            Fogo fogo_filho = new Fogo("fogo.png");
            fogo_filho.setPotencia(this.potencia - 1);
            fogo_filho.setDirecao(this.direcao);
            fogo_filho.setPosicao(this.pPosicao.getLinha() + offset_linha, this.pPosicao.getColuna() + offset_coluna);
            Desenhador.getTelaDoJogo().addElemento(fogo_filho);
            fogo_filho.propaga();
        }     
        
    }
}
