package Modelo;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.io.IOException;
import java.io.Serializable;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Auxiliar.Consts;
import Auxiliar.Desenhador;
import Auxiliar.Posicao;
import Controler.ControleDeJogo;
import Controler.Tela;
import java.util.ArrayList;


public class Fogo extends Elemento {

    private int potencia;
    private int direcao;

    public Fogo() {
        super("explosao.png");
        this.potencia = 1;
        this.direcao = 0;
    }
    
    public void setPotencia(int p) {
        if (p > -1) {
            this.potencia = p;
        }
    }
    
    public void setDirecao(int d) {
        this.direcao = d;
    }
    
    public void propaga(Tela t) {
        Desenhador.getTelaDoJogo().addElemento(this);
                
        if (potencia > 1) {
            Posicao offset = this.getPosicao().offset(0, 0);
            switch (this.direcao) {
                case Consts.UP:
                    offset.moveUp();
                    break;
                case Consts.DOWN:
                    offset.moveDown();
                    break;
                case Consts.LEFT:
                    offset.moveLeft();
                    break;
                case Consts.RIGHT:
                    offset.moveRight();
                    break;
            }
            
            if (t.ehPosicaoValida(offset)) {
                Fogo fogo_filho = new Fogo();
                fogo_filho.setPotencia(this.potencia - 1);
                fogo_filho.setDirecao(this.direcao);
                fogo_filho.setPosicao(offset.getColuna(), offset.getLinha());
                fogo_filho.propaga(t);
            }
            
            ArrayList<Elemento> elementos = (ArrayList<Elemento>) t.buscaElemento(offset).clone(); 
            for (Elemento e: elementos) {
                e.touchFire();
            }
            
        }
        vanish();
    }
    
    public void vanish() {
        clocks.add(new Clock(
                10,
                1,
                null,
                this::remove,
                false
        ));
    }
    
    public void touchAnother(Elemento e) {
        e.touchFire();
    }
}
