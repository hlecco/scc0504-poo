package Controler;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

import Auxiliar.*;
import Model.Element;
import Model.ElementComparator;


public class Screen extends javax.swing.JFrame implements KeyListener {

    private ArrayList<Element> elements;
    private ArrayList<ArrayList<ArrayList<Element>>> elementsMatrix;
    private ArrayList<Element> removedElements;
    private GameControl control = new GameControl();
    private Graphics g2;
    private Position bombermanPosition;

    
    public Screen() throws IOException {
        initComponents();
        bombermanPosition = new Position(1, 1);
        removedElements = new ArrayList<Element>();
        elements = new ArrayList<Element>(1089);
        elementsMatrix = new ArrayList<ArrayList<ArrayList<Element>>>(Consts.RES);

        /* Cria a janela do tamanho do cenario + insets (bordas) da janela */
        this.setSize(Consts.RES * Consts.CELL_SIDE + getInsets().left + getInsets().right,
                Consts.RES * Consts.CELL_SIDE + getInsets().top + getInsets().bottom);

        for (int i = 0; i < Consts.RES; i++) {
            elementsMatrix.add(new ArrayList<ArrayList<Element>>(Consts.RES));
            for (int j = 0; j < Consts.RES; j++) {
                elementsMatrix.get(i).add(new ArrayList<Element>());
            }
        }
    }

    /*
    Função que iniciará o jogo criando a primeira fase.
    */
    public void start() {
        Draw.setScene(this);
        this.addKeyListener(this);

        try {
            Fase fase = new Fase(1);
            fase.print(3, 1, 1, 4, this);
        } catch (FileNotFoundException f) {
            System.out.println(f.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addElement(Element umElemento) {
        elements.add(umElemento);
        elementsMatrix.get(umElemento.getPosicao().getCol())
                .get(umElemento.getPosicao().getRow())
                .add(umElemento);
        elements.sort(new ElementComparator());
    }

    public void trueRemoveElemento(Element umElemento) {
        elements.remove(umElemento);
        elementsMatrix.get(umElemento.getPosicao().getCol())
                .get(umElemento.getPosicao().getRow())
                .remove(umElemento);
    }

    public void removeElement(Element umElemento) {
        removedElements.add(umElemento);
    }

    public void moveElement(Element pElement) {
        if (elementsMatrix.get(pElement.getPosicao().getPreviousPosition().getCol())
                .get(pElement.getPosicao().getPreviousPosition().getRow())
                .remove(pElement)) {
            elementsMatrix.get(pElement.getPosicao().getCol())
                    .get(pElement.getPosicao().getRow())
                    .add(pElement);
        }
    }

    public ArrayList<Element> getElements() {
        return elements;
    }

    public ArrayList<Element> searchElement(Position p) {
        return elementsMatrix.get(p.getCol()).get(p.getRow());
    }

    public boolean isValidPosition(Position p) {
        for (Element e : this.searchElement(p)) {
            if (!e.isbTransposable()) {
                return false;
            }
        }
        return true;
    }

    // tá sendo usado?
    public boolean isOnFire(Position p) {
        for (Element e : this.searchElement(p)) {
            if (e.isDefeats()) {
                return true;
            }
        }
        return false;
    }

    public boolean hasElement(Position p) {
        return (!this.searchElement(p).isEmpty());
    }

    public Graphics getGraphicsBuffer() {
        return g2;
    }

    /*
    Método executado a cada Consts.FRAME_INTERVAL milissegundos para
    redeenhar a tela inteira.
    */
    @Override
    public void paint(Graphics gOld) {
        Graphics g = this.getBufferStrategy().getDrawGraphics();
        /* Cria o contexto gráfico */
        g2 = g.create(getInsets().left, getInsets().top,
                getWidth() - getInsets().right, getHeight() - getInsets().top);

        /* Desenha cenário */
        for (int i = 0; i < Consts.RES; i++) {
            for (int j = 0; j < Consts.RES; j++) {
                try {
                    Image newImage = Toolkit.getDefaultToolkit()
                            .getImage(new java.io.File(".")
                                    .getCanonicalPath()
                                    + Consts.PATH + "background.png");
                    g2.drawImage(newImage, j * Consts.CELL_SIDE,
                            i * Consts.CELL_SIDE, Consts.CELL_SIDE,
                            Consts.CELL_SIDE, null);
                } catch (IOException ex) {
                    Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        /* Aqui podem ser inseridos novos processamentos de controle */
        if (!this.elements.isEmpty()) {
            this.control.processAll((ArrayList<Element>) elements.clone());
            this.control.drawAll((ArrayList<Element>) elements.clone());
            for (Element e : removedElements) {
                this.trueRemoveElemento(e);
            }
            removedElements.clear();
        }

        g.dispose();
        g2.dispose();
        if (!getBufferStrategy().contentsLost()) {
            getBufferStrategy().show();
        }
    }

    /*
    Função que limpa a tela, ie, remove todos os elementos do vetor elements.
     */
    public void clearStage() {
        for (Element e : (ArrayList<Element>) this.elements.clone()) {
            this.removeElement(e);
        }
    }

    /*
    Função que fará a mudança de fases. Limpa a tela atual e chama o método
    print da Fase, que colocará a imagem de transição na tela e ao final chamará
    a função read.
     */
    public void nextFase(int numFase, int numLife, int bomberUp, int fireUp,
            int speedUp) {
        this.clearStage();

        try {
            Fase fase = new Fase(numFase);
            fase.print(numLife, bomberUp, fireUp, speedUp, this);
        } catch (IOException ex) {
            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    Reinicia o jogo (ocorrerá quando o número de vidas do Bomberman zerar).
     */
    public void resetGame() {
        this.clearStage();
        this.nextFase(1, 3, 1, 1, 4);
    }

    public void setBombermanPosition(Position p) {
        this.bombermanPosition.setPosition(p.getCol(), p.getRow());
    }

    public Position getBombermanPosition() {
        return this.bombermanPosition;
    }

    public void go() {
        TimerTask redraw = new TimerTask() {
            @Override
            public void run() {
                /* Executa o método paint */
                repaint();
            }
        };

        /* Redesenha (executa o metodo paint) tudo a cada
        Consts.FRAME_INTERVAL milissegundos */
        Timer timer = new Timer();
        timer.schedule(redraw, 0, Consts.FRAME_INTERVAL);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        for (Element el : (ArrayList<Element>) elements.clone()) {
            el.Event(e.getKeyCode(), control, this);
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

    public void close() {
        this.clearStage();
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
