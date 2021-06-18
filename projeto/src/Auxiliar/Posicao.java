package Auxiliar;

import java.io.Serializable;
import static java.lang.Math.abs;

public class Posicao implements Serializable {
    private int	linha;
    private int coluna;
    
    private int linhaAnterior;
    private int colunaAnterior;

    public Posicao(int coluna, int linha){
        this.setPosicao(coluna, linha);
    }

    public boolean setPosicao(int coluna, int linha) {       
        if(linha < 0 || linha >= Auxiliar.Consts.RES)
            return false;
        linhaAnterior = this.linha;
        this.linha = linha;
        
        if(coluna < 0 || coluna >= Auxiliar.Consts.RES)
            return false;
        colunaAnterior = this.coluna;
        this.coluna = coluna;
        return true;
    }
    
    public int getLinha(){
        return linha;
    }
   
    public boolean volta(){
        return this.setPosicao(colunaAnterior, linhaAnterior);
    }
    
    public Posicao getPosicaoAnterior() {
        return new Posicao(colunaAnterior, linhaAnterior);
    }

    public int getColuna(){
        return coluna;
    }

    public boolean estaNaMesmaPosicao(Posicao posicao){
        return (linha == posicao.getLinha() && coluna == posicao.getColuna());
    }

    public boolean copia(Posicao posicao){
        return this.setPosicao(posicao.getLinha(),posicao.getColuna());
    }
    
    
    public boolean moveUp(){
        return this.setPosicao(this.getColuna(), this.getLinha()-1);
    }
    public boolean moveDown(){
        return this.setPosicao(this.getColuna(), this.getLinha()+1);
    }
    public boolean moveRight(){
        return this.setPosicao(this.getColuna()+1, this.getLinha());
    }
    public boolean moveLeft(){
        return this.setPosicao(this.getColuna()-1, this.getLinha());        
    }
    
    public Posicao offset(int x, int y) {
        Posicao novaPosicao = new Posicao(
                this.getColuna() + x,
                this.getLinha() + y
        );
        return novaPosicao;
    }
    
    public int distanceTo(Posicao p) {
        return abs(this.getColuna() - p.getColuna()) + abs(this.getLinha() - p.getLinha());
    }
    
    public boolean equals(Posicao p) {
        if (this == p) {
            return true;
        }
        
        if (p == null || getClass() != p.getClass()) {
            return false;
        }
        
        if (p.getLinha() == this.getLinha() && p.getColuna() == this.getColuna()) {
            return true;
        }
        
        return false;
    }
}