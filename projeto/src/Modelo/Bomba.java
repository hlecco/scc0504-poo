package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenhador;
import Auxiliar.Posicao;
import Controler.ControleDeJogo;
import Controler.Tela;


public class Bomba extends Elemento {

    private int potencia;

    public Bomba() {
        super("bomb.png");
        this.potencia = 1;
        this.bTransponivel = false;
    }
    
    public void setPotencia(int p) {
        if (p > 0) {
            this.potencia = p;
        }
    }
    
    public void explode(ControleDeJogo c, Tela t) {
        int valido;
        Posicao offset = new Posicao(pPosicao.getLinha(), pPosicao.getColuna());
        Fogo fogo = new Fogo();
        fogo.setPosicao(offset.getLinha(), offset.getColuna());
        Desenhador.getTelaDoJogo().addElemento(fogo);
        fogo.vanish();
        
        offset.moveUp();
        valido = c.ehPosicaoValida(t.getElementos(), offset);
        if (valido == 1) {
            Fogo fogo_up = new Fogo();
            fogo_up.setPotencia(this.potencia);
            fogo_up.setDirecao(Consts.UP);
            fogo_up.setPosicao(offset.getLinha(), offset.getColuna());
            fogo_up.propaga(c, t);
        }
        
        if (valido == 2) {
            Elemento e = t.buscaElemento(offset);
            if (e != null) {
                e.destroiElemento();
            }
        }
        
        offset.volta();
        offset.moveDown();
        valido = c.ehPosicaoValida(t.getElementos(), offset);
        if (valido == 1) {
            Fogo fogo_down = new Fogo();
            fogo_down.setPotencia(this.potencia);
            fogo_down.setDirecao(Consts.DOWN);
            fogo_down.setPosicao(offset.getLinha(), offset.getColuna());
            fogo_down.propaga(c, t);
        }
        
        if (valido == 2) {
            Elemento e = t.buscaElemento(offset);
            if (e != null) {
                e.destroiElemento();
            }
        }
        
        offset.volta();
        offset.moveLeft();
        valido = c.ehPosicaoValida(t.getElementos(), offset);
        if (c.ehPosicaoValida(t.getElementos(), offset) == 1) {
            Fogo fogo_left = new Fogo();
            fogo_left.setPotencia(this.potencia);
            fogo_left.setDirecao(Consts.LEFT);
            fogo_left.setPosicao(offset.getLinha(), offset.getColuna());
            fogo_left.propaga(c, t);
        }
        
        if (valido == 2) {
            Elemento e = t.buscaElemento(offset);
            if (e != null) {
                e.destroiElemento();
            }
        }
        
        offset.volta();
        offset.moveRight();
        valido = c.ehPosicaoValida(t.getElementos(), offset);
        if (c.ehPosicaoValida(t.getElementos(), offset) == 1) {
            Fogo fogo_right = new Fogo();
            fogo_right.setPotencia(this.potencia);
            fogo_right.setDirecao(Consts.RIGHT);
            fogo_right.setPosicao(offset.getLinha(), offset.getColuna());
            fogo_right.propaga(c, t);
        }
        
        if (valido == 2) {
            Elemento e = t.buscaElemento(offset);
            if (e != null) {
                e.destroiElemento();
            }
        }
        
        Desenhador.getTelaDoJogo().removeElemento(this);
    }
}
