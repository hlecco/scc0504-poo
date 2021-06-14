package Modelo;

import Auxiliar.Consts;
import Auxiliar.Desenhador;
import Auxiliar.Posicao;
import Controler.ControleDeJogo;
import Controler.Tela;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;


public class Bomba extends Elemento {

    private int potencia;
    private TimerTask timer;

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
    
    public void setUp() {
        Bomba bomb = this;
        timer = new TimerTask() {
            public void run() {
                bomb.explode();
            }
        };
        Timer bombtimer = new Timer();
        bombtimer.schedule(timer, 30 * Consts.FRAME_INTERVAL);
    }
    
    public void explode() {
        timer.cancel();
        Desenhador.getTelaDoJogo().removeElemento(this);
        ArrayList<Elemento> elementos;
        
        boolean valido;
        Posicao offset = new Posicao(pPosicao.getColuna(), pPosicao.getLinha());
        Fogo fogo = new Fogo();
        
        fogo.setPosicao(offset.getColuna(), offset.getLinha());
        Tela t = Desenhador.getTelaDoJogo();
        t.addElemento(fogo);
        fogo.vanish();
        
        offset.moveUp();
        valido = t.ehPosicaoValida(offset);
        if (valido) {
            Fogo fogo_up = new Fogo();
            fogo_up.setPotencia(this.potencia);
            fogo_up.setDirecao(Consts.UP);
            fogo_up.setPosicao(offset.getColuna(), offset.getLinha());
            fogo_up.propaga(t);
        }
        elementos = (ArrayList<Elemento>) t.buscaElemento(offset).clone(); 
        for (Elemento e: elementos) {
            e.touchFire();
        }
        
        offset.volta();
        offset.moveDown();
        valido = t.ehPosicaoValida(offset);
        if (valido) {
            Fogo fogo_down = new Fogo();
            fogo_down.setPotencia(this.potencia);
            fogo_down.setDirecao(Consts.DOWN);
            fogo_down.setPosicao(offset.getColuna(), offset.getLinha());
            fogo_down.propaga(t);
        }
        elementos = (ArrayList<Elemento>) t.buscaElemento(offset).clone(); 
        for (Elemento e: elementos) {
            e.touchFire();
        }
        
        offset.volta();
        offset.moveLeft();
        valido = t.ehPosicaoValida(offset);
        if (valido) {
            Fogo fogo_left = new Fogo();
            fogo_left.setPotencia(this.potencia);
            fogo_left.setDirecao(Consts.LEFT);
            fogo_left.setPosicao(offset.getColuna(), offset.getLinha());
            fogo_left.propaga(t);
        }
        elementos = (ArrayList<Elemento>) t.buscaElemento(offset).clone(); 
        for (Elemento e: elementos) {
            e.touchFire();
        }
        
        offset.volta();
        offset.moveRight();
        valido = t.ehPosicaoValida(offset);
        if (valido) {
            Fogo fogo_right = new Fogo();
            fogo_right.setPotencia(this.potencia);
            fogo_right.setDirecao(Consts.RIGHT);
            fogo_right.setPosicao(offset.getColuna(), offset.getLinha());
            fogo_right.propaga(t);
        }
        elementos = (ArrayList<Elemento>) t.buscaElemento(offset).clone(); 
        for (Elemento e: elementos) {
            e.touchFire();
        }
    }
    
    public void touchFire() {
        this.explode();
    }
}
