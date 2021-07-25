package Controller;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

import Auxiliar.*;
import Model.Bomberman;
import Model.Element;
import Model.ElementComparator;

public class Screen extends javax.swing.JFrame implements KeyListener {

    private final ArrayList<Element> elements;
    private final ArrayList<ArrayList<ArrayList<Element>>> elementsMatrix;
    private final ArrayList<Element> removedElements;
    private final GameControl control = new GameControl();
    private Graphics g2;
    private final Position bombermanPosition;
    private final Stage stage;
    private String background;

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

        this.stage = new Stage();
    }

    public void setBackgroundImage(String filename) {
        this.background = filename;
    }

    /*
    Função que iniciará o jogo criando a primeira fase.
     */
    public void startGame() {
        Draw.setScene(this);
        this.addKeyListener(this);
        this.setBackgroundImage("blank.png");
        Stage.start(this);
    }

    public void startFirstStage() {
        this.setBackgroundImage("background.png");
        this.clearStage();

        try {
            stage.setStage(1);
            stage.print(this);
        } catch (FileNotFoundException f) {
            System.out.println(f.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addElement(Element umElemento) {
        elements.add(umElemento);
        elementsMatrix.get(umElemento.getPosition().getCol())
                .get(umElemento.getPosition().getRow())
                .add(umElemento);
        elements.sort(new ElementComparator());
    }

    /*
    Remove os elementos de fato. Chamado no paint.
     */
    public void trueRemoveElemento(Element umElemento) {
        elements.remove(umElemento);
        elementsMatrix.get(umElemento.getPosition().getCol())
                .get(umElemento.getPosition().getRow())
                .remove(umElemento);
    }

    /*
    Método que adiciona um elemento para remoção. A remoção em si será feita
    no paint.
     */
    public void removeElement(Element umElemento) {
        removedElements.add(umElemento);
    }

    /*
    Método que remove o elemento da posição anterior quando ele muda de posição.
     */
    public void moveElement(Element pElement) {
        if (elementsMatrix.get(pElement.getPosition().getPreviousPosition().getCol())
                .get(pElement.getPosition().getPreviousPosition().getRow())
                .remove(pElement)) {
            elementsMatrix.get(pElement.getPosition().getCol())
                    .get(pElement.getPosition().getRow())
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
                                    + Consts.PATH + this.background);
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
    print da Stage, que colocará a imagem de transição na tela e ao final chamará
    a função read.
     */
    public void nextStage() {
        this.clearStage();
        SaveAndLoad.getInstance().terminate();
        try {
            Thread.sleep(3100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            stage.next();
            stage.print(this);
        } catch (IOException ex) {
            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void resetStage() {
        this.clearStage();
        SaveAndLoad.getInstance().terminate();
        try {
            Thread.sleep(3100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            stage.print(this);
        } catch (IOException ex) {
            stage.start(this);
        }
    }

    /*
    Reinicia o jogo (ocorrerá quando o número de vidas do Bomberman zerar).
     */
    public void resetGame() {
        this.clearStage();
        SaveAndLoad.getInstance().terminate();
        try {
            Thread.sleep(3100);
        } catch (InterruptedException ex) {
            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.setBackgroundImage("blank.png");
        stage.start(this);
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
