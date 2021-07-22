package Model;

import java.util.ArrayList;

import Auxiliar.Consts;
import Auxiliar.Draw;
import Auxiliar.Position;
import Controler.Screen;
import java.io.Serializable;


public class Bomb extends Element implements Serializable {

    private int potency;
    private boolean blownUp;
    

    public Bomb() {
        super("bomb.png", 5);
        this.potency = 1;
        this.bTransposable = false;
        this.blownUp = false;
    }
    
    public void setPotency(int p) {
        if (p > 0) {
            this.potency = p;
        }
    }
    
    /*
    Método que faz com que a bomba exploda após x segundos.
    */
    public void setUp() {
        clocks.add(new Clock(Consts.BOMB_TIMER, 8,
                this.sprite::cycle, this::explode, false));
    }
    
    /*
    Método que faz a bomba explodir. Essencialmente tal método criará objetos
    da classe Fire após verificar em qual direção é possível propagar o fogo
    e em seguida faz o fogo propagar chamando o método propagate da classe Fire.
    */
    public void explode() {
        if (this.blownUp) {
            return;
        }
        
        this.blownUp = true;
        this.remove();
        ArrayList<Element> elements;
        
        boolean valid;
        Position offset = new Position(position.getCol(), position.getRow());
        Fire fire = new Fire();
        
        fire.setPosition(offset.getCol(), offset.getRow());
        Screen t = Draw.getScreen();
        t.addElement(fire);
        
        offset.moveUp();
        valid = t.isValidPosition(offset);
        elements = (ArrayList<Element>) t.searchElement(offset).clone(); 
        for (Element e : elements) {
            e.touchFire();
        }
        if (valid) {
            Fire fireUp = new Fire();
            fireUp.setPotency(this.potency);
            fireUp.setDirection(Consts.UP);
            fireUp.setPosition(offset.getCol(), offset.getRow());
            fireUp.addClock(1, 1, null, fireUp::propagate, false);
            t.addElement(fireUp);
        }
        
        offset.goBack();
        offset.moveDown();
        valid = t.isValidPosition(offset);
        elements = (ArrayList<Element>) t.searchElement(offset).clone(); 
        for (Element e : elements) {
            e.touchFire();
        }
        if (valid) {
            Fire fireDown = new Fire();
            fireDown.setPotency(this.potency);
            fireDown.setDirection(Consts.DOWN);
            fireDown.setPosition(offset.getCol(), offset.getRow());
            fireDown.addClock(1, 1, null, fireDown::propagate, false);
            t.addElement(fireDown);
        }
        
        offset.goBack();
        offset.moveLeft();
        valid = t.isValidPosition(offset);
        elements = (ArrayList<Element>) t.searchElement(offset).clone(); 
        for (Element e : elements) {
            e.touchFire();
        }
        if (valid) {
            Fire fireLeft = new Fire();
            fireLeft.setPotency(this.potency);
            fireLeft.setDirection(Consts.LEFT);
            fireLeft.setPosition(offset.getCol(), offset.getRow());
            fireLeft.addClock(1, 1, null, fireLeft::propagate, false);
            t.addElement(fireLeft);
        }
        
        
        offset.goBack();
        offset.moveRight();
        valid = t.isValidPosition(offset);
        elements = (ArrayList<Element>) t.searchElement(offset).clone(); 
        for (Element e : elements) {
            e.touchFire();
        }
        if (valid) {
            Fire fogoRight = new Fire();
            fogoRight.setPotency(this.potency);
            fogoRight.setDirection(Consts.RIGHT);
            fogoRight.setPosition(offset.getCol(), offset.getRow());
            fogoRight.addClock(2, 1, null, fogoRight::propagate, false);
            t.addElement(fogoRight);
        }
        
    }
    
    /*
    Método que explode a bomba caso o fogo de uma outra bomba toque ela.
    */
    @Override
    public void touchFire() {
        this.explode();
    }
    
}