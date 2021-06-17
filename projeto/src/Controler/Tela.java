package Controler;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;
import java.util.zip.*;

import Modelo.*;
import Auxiliar.*;


public class Tela extends javax.swing.JFrame implements KeyListener {

    private ArrayList<Elemento> eElementos;
    private ArrayList<ArrayList<ArrayList<Elemento>>> elementoMatrix;
    private ControleDeJogo cControle = new ControleDeJogo();
    private Graphics g2;

    public Tela() throws IOException {
        Desenhador.setCenario(this); /*Desenhador funciona no modo estatico*/
        initComponents();
 
        this.addKeyListener(this);   /*teclado*/
        
        /*Cria a janela do tamanho do cenario + insets (bordas) da janela*/
        this.setSize(Consts.RES * Consts.CELL_SIDE + getInsets().left + getInsets().right,
                Consts.RES * Consts.CELL_SIDE + getInsets().top + getInsets().bottom);

        
        /*Cria eElementos adiciona elementos*/
        /*O protagonista (heroi) necessariamente precisa estar na posicao 0 do array*/
        eElementos = new ArrayList<Elemento>(1089);
        elementoMatrix = new ArrayList<ArrayList<ArrayList<Elemento>>>(Consts.RES);
        for (int i=0; i<Consts.RES; i++) {
            elementoMatrix.add(new ArrayList<ArrayList<Elemento>>(Consts.RES));            
            for (int j=0; j<Consts.RES; j++) {
                elementoMatrix.get(i).add(new ArrayList<Elemento>());
            }
        }
        FaseReader reader = new FaseReader();
        reader.read("fase1.txt", this);
    }

/*--------------------------------------------------*/
    public void addElemento(Elemento umElemento) {
        eElementos.add(umElemento);
        elementoMatrix.get(umElemento.getPosicao().getColuna())
                      .get(umElemento.getPosicao().getLinha())
                      .add(umElemento);
        eElementos.sort(new ElementComparator());
    }

    public void removeElemento(Elemento umElemento) {
        eElementos.remove(umElemento);
        elementoMatrix.get(umElemento.getPosicao().getColuna())
                      .get(umElemento.getPosicao().getLinha())
                      .remove(umElemento);
    }
    
    public void moveElemento(Elemento umElemento) {
        if (elementoMatrix.get(umElemento.getPosicao().getPosicaoAnterior().getColuna())
                          .get(umElemento.getPosicao().getPosicaoAnterior().getLinha())
                          .remove(umElemento)) {
            elementoMatrix.get(umElemento.getPosicao().getColuna())
                          .get(umElemento.getPosicao().getLinha())
                          .add(umElemento);
        }
    }
    
    public ArrayList<Elemento> getElementos() {
        return eElementos;
    }
    
    public ArrayList<Elemento> buscaElemento(Posicao p) {
        return elementoMatrix.get(p.getColuna()).get(p.getLinha());
    }
    
    public boolean ehPosicaoValida(Posicao p) {
        for (Elemento e : this.buscaElemento(p)) {
            if (!e.isbTransponivel()) {
                return false;
            }
        }
        return true;
    }
    
    public boolean temElemento(Posicao p) {
        return (!this.buscaElemento(p).isEmpty());
    }

    public Graphics getGraphicsBuffer() {
        return g2;
    }
    
    /*Este metodo eh executado a cada Consts.FRAME_INTERVAL milissegundos*/    
    public void paint(Graphics gOld) {
        Graphics g = this.getBufferStrategy().getDrawGraphics();
        /*Criamos um contexto gráfico*/
        g2 = g.create(getInsets().left, getInsets().top, getWidth() - getInsets().right, getHeight() - getInsets().top);

        /*Desenha cenário*/
        for (int i = 0; i < Consts.RES; i++) {
            for (int j = 0; j < Consts.RES; j++) {
                try {
                    /*Linha para alterar o background*/
                    Image newImage = Toolkit.getDefaultToolkit().getImage(new java.io.File(".").getCanonicalPath() + Consts.PATH + "background.png");
                    g2.drawImage(newImage, j * Consts.CELL_SIDE,
                            i * Consts.CELL_SIDE, Consts.CELL_SIDE, Consts.CELL_SIDE, null);

                } catch (IOException ex) {
                    Logger.getLogger(Tela.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
        /*Aqui podem ser inseridos novos processamentos de controle*/
        if (!this.eElementos.isEmpty()) {
            this.cControle.processaTudo(eElementos);
            this.cControle.desenhaTudo(eElementos);
        }

        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }

    public void go() {
        TimerTask redesenhar = new TimerTask() {
            public void run() {
                repaint(); /*(executa o metodo paint)*/
            }
        };        
        
        /*Redesenha (executa o metodo paint) tudo a cada Consts.FRAME_INTERVAL milissegundos*/
        Timer timer = new Timer();
        timer.schedule(redesenhar, 0, Consts.FRAME_INTERVAL);
    }

    public void keyPressed(KeyEvent e) {
        for (Elemento el : (ArrayList<Elemento>) eElementos.clone()) {
            el.Event(e.getKeyCode(), cControle, this);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("POO2021");
        setAutoRequestFocus(false);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 561, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 500, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables


    public void keyTyped(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }
}
