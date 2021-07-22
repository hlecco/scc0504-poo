package Controler;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

import Auxiliar.*;
import Model.Element;
import Model.ElementComparator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Screen extends javax.swing.JFrame implements KeyListener {

    private final ArrayList<Element> elements;
    private final ArrayList<ArrayList<ArrayList<Element>>> elementsMatrix;
    private final ArrayList<Element> removedElements;
    private final GameControl control = new GameControl();
    private Graphics g2;
    private final Position bombermanPosition;
    private final int timerSave;

    public Screen() throws IOException {
        initComponents();
        bombermanPosition = new Position(1, 1);
        removedElements = new ArrayList<Element>();
        elements = new ArrayList<Element>(1089);
        elementsMatrix = new ArrayList<ArrayList<ArrayList<Element>>>(Consts.RES);
        timerSave = 30000; // ver como vai fazer pra salvar, esse
        // 30000 significa que vai salvar a cada 30 segundos.

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
        this.nextStage(1, 3, 1, 1, 4);
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
    print da Stage, que colocará a imagem de transição na tela e ao final chamará
    a função read.
     */
    public void nextStage(int numFase, int numLife, int bomberUp, int fireUp,
            int speedUp) {
        this.clearStage();

        try {
            Stage stage = new Stage(numFase);
            Autosave as = new Autosave(this, timerSave);
            stage.print(numLife, bomberUp, fireUp, speedUp, this, as);
        } catch (IOException ex) {
            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /*
    Reinicia o jogo (ocorrerá quando o número de vidas do Bomberman zerar).
     */
    public void resetGame() {
        this.nextStage(1, 3, 1, 1, 4);
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

    public void save() {
        System.out.println("oi");
        File zipfile = new File("./load/zipfile.zip");
        try {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipfile));
            zos.putNextEntry(new ZipEntry("classes.txt"));
            for (Element e : this.getElements()) {
                String temp = e.getClass().toString() + "\n";
                zos.write(temp.getBytes(), 0, temp.length());
            }
            zos.closeEntry();
            zos.putNextEntry(new ZipEntry("objects.dat"));
            ObjectOutputStream oos = new ObjectOutputStream(zos);
            for (Element e : this.getElements()) {
                oos.writeObject(e);
            }
            zos.closeEntry();
            oos.close();
            zos.close();
        } catch (IOException ex) {
            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

}
