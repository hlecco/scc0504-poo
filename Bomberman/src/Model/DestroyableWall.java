package Model;

import java.io.Serializable;

import Auxiliar.Draw;


public class DestroyableWall extends Element implements Serializable {
    
    private int hidden;
    
    
    public DestroyableWall(int pHidden) {
        super("splitwall.png");
        this.bTransposable = false;
        this.bDestroyable = true;
        this.hidden = pHidden;
    }

    @Override
    public void selfDraw() {
        super.selfDraw();
    }
    
    /*
    Método que destrói o muro ao tocar o fogo.
    */
    @Override
    public void touchFire() {
        Draw.getScreen().removeElement(this);
        Element hidden_element = null;
        
        switch (hidden) {
            case 0:
                break;
            case 1:
                hidden_element = new BomberUp();
                break;
            case 2:
                hidden_element = new FireUp();
                break;
            case 3:
                hidden_element = new Door();
                break;
            case 4:
                hidden_element = new SpeedUp();
        }
        
        if (hidden_element != null) {
            hidden_element.setPosition(this.getPosicao().getCol(), this.getPosicao().getRow());
            Draw.getScreen().addElement(hidden_element);
        }
    }
    
}