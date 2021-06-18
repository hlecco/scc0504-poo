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
        super("explosao.png", 1, 1, 6, 0, 0);
        this.potencia = 1;
        this.direcao = 0;
        this.bMortal = true;
        this.defeats = true;
        this.vanish();
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
        Tela t = Desenhador.getTelaDoJogo();
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
                this.addClock(1, 1, null, fogo_filho::propaga, false);
                t.addElemento(fogo_filho);
            }
            
            ArrayList<Elemento> elementos = (ArrayList<Elemento>) t.buscaElemento(offset).clone(); 
            for (Elemento e: elementos) {
                e.touchFire();
            }
        }
    }
    
    public void vanish() {
        clocks.add(new Clock(
                6,
                2,
                this.sprite::cycle,
                this::remove,
                false
        ));
        clocks.add(new Clock(
                3,
                2,
                null,
                this::turnHarmless,
                false
        ));
    }
    
    public void turnHarmless() {
        this.bMortal = false;
    }
    
    public void touchAnother(Elemento e) {
        e.touchFire();
        if (this.bMortal) {
            e.touchEnemy();
        }
    }
}
