package Auxiliar;

import java.io.Serializable;
import static java.lang.Math.abs;


public class Position implements Serializable {
    
    private int	row;
    private int col;
    private int previousRow;
    private int previousCol;
    

    public Position(int pCol, int pRow) {
        row = pRow;
        col = pCol;
    }

    public boolean setPosition(int pCol, int pRow) {       
        if (pRow < 0 || pRow >= Auxiliar.Consts.RES)
            return false;
        
        if (pCol < 0 || pCol >= Auxiliar.Consts.RES)
            return false;
        
        previousRow = this.row;
        this.row = pRow;        
        previousCol = this.col;
        this.col = pCol;
        
        return true;
    }
    
    public int getRow() {
        return row;
    }
   
    public boolean goBack() {
        return this.setPosition(previousCol, previousRow);
    }
    
    public Position getPreviousPosition() {
        return new Position(previousCol, previousRow);
    }

    public int getCol() {
        return col;
    }

    public boolean isAtSamePosition(Position position) {
        return (row == position.getRow() && col == position.getCol());
    }

    public boolean copy(Position posicao) {
        return this.setPosition(posicao.getRow(), posicao.getCol());
    }
    
    public boolean moveUp() {
        return this.setPosition(this.getCol(), this.getRow() - 1);
    }
    
    public boolean moveDown() {
        return this.setPosition(this.getCol(), this.getRow() + 1);
    }
    
    public boolean moveRight() {
        return this.setPosition(this.getCol() + 1, this.getRow());
    }
    
    public boolean moveLeft() {
        return this.setPosition(this.getCol() - 1, this.getRow());        
    }
    
    /*
    Realiza um deslocamento na posição do elemento de acordo com os parâmetros.
    */
    public Position offset(int x, int y) {
        Position newPosition = new Position(this.getCol() + x, this.getRow() + y);
        return newPosition;
    }
    
    /*
    Calcula a distância entre dois elementos usando a geometria do táxi.
    */
    public int distanceTo(Position p) {
        return abs(this.getCol() - p.getCol()) + abs(this.getRow() - p.getRow());
    }
    
    public boolean equals(Position p) {
        if (this == p) {
            return true;
        }
        
        if (p == null || getClass() != p.getClass()) {
            return false;
        }
        
        if (p.getRow() == this.getRow() && p.getCol() == this.getCol()) {
            return true;
        }
        
        return false;
    }
    
}