package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenhador;
import Auxiliar.Posicao;
import Controler.ControleDeJogo;
import Controler.Tela;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;


public class Bomba extends Elemento {

    private int potencia;
    private boolean blownUp;

    public Bomba() {
        super("bomb.png", 5);
        this.potencia = 1;
        this.bTransponivel = false;
        this.blownUp = false;
    }
    
    public void setPotencia(int p) {
        if (p > 0) {
            this.potencia = p;
        }
    }
    
    public void setUp() {
        clocks.add(new Clock(
                Consts.BOMB_TIMER,
                8,
                this.sprite::cycle,
                this::explode,
                false
        ));
    }
    
    public void explode() {
        if (this.blownUp) {
            return;
        }
        this.blownUp = true;
        this.remove();
        ArrayList<Elemento> elementos;
        
        boolean valido;
        Posicao offset = new Posicao(pPosicao.getColuna(), pPosicao.getLinha());
        Fogo fogo = new Fogo();
        
        fogo.setPosicao(offset.getColuna(), offset.getLinha());
        Tela t = Desenhador.getTelaDoJogo();
        t.addElemento(fogo);
        
        offset.moveUp();
        valido = t.ehPosicaoValida(offset);
        elementos = (ArrayList<Elemento>) t.buscaElemento(offset).clone(); 
        for (Elemento e: elementos) {
            e.touchFire();
        }
        if (valido) {
            Fogo fogo_up = new Fogo();
            fogo_up.setPotencia(this.potencia);
            fogo_up.setDirecao(Consts.UP);
            fogo_up.setPosicao(offset.getColuna(), offset.getLinha());
            fogo_up.addClock(1, 1, null, fogo_up::propaga, false);
            t.addElemento(fogo_up);
        }
        
        offset.volta();
        offset.moveDown();
        valido = t.ehPosicaoValida(offset);
        elementos = (ArrayList<Elemento>) t.buscaElemento(offset).clone(); 
        for (Elemento e: elementos) {
            e.touchFire();
        }
        if (valido) {
            Fogo fogo_down = new Fogo();
            fogo_down.setPotencia(this.potencia);
            fogo_down.setDirecao(Consts.DOWN);
            fogo_down.setPosicao(offset.getColuna(), offset.getLinha());
            fogo_down.addClock(1, 1, null, fogo_down::propaga, false);
            t.addElemento(fogo_down);
        }
        
        offset.volta();
        offset.moveLeft();
        valido = t.ehPosicaoValida(offset);
        elementos = (ArrayList<Elemento>) t.buscaElemento(offset).clone(); 
        for (Elemento e: elementos) {
            e.touchFire();
        }
        if (valido) {
            Fogo fogo_left = new Fogo();
            fogo_left.setPotencia(this.potencia);
            fogo_left.setDirecao(Consts.LEFT);
            fogo_left.setPosicao(offset.getColuna(), offset.getLinha());
            fogo_left.addClock(1, 1, null, fogo_left::propaga, false);
            t.addElemento(fogo_left);
        }
        
        
        offset.volta();
        offset.moveRight();
        valido = t.ehPosicaoValida(offset);
        elementos = (ArrayList<Elemento>) t.buscaElemento(offset).clone(); 
        for (Elemento e: elementos) {
            e.touchFire();
        }
        if (valido) {
            Fogo fogo_right = new Fogo();
            fogo_right.setPotencia(this.potencia);
            fogo_right.setDirecao(Consts.RIGHT);
            fogo_right.setPosicao(offset.getColuna(), offset.getLinha());
            fogo_right.addClock(2, 1, null, fogo_right::propaga, false);
            t.addElemento(fogo_right);
        }
        
    }
    
    public void touchFire() {
        this.explode();
    }
}
