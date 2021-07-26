package Controller;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;
import java.util.logging.*;

import Auxiliar.*;
import Model.Element;
import Model.ElementComparator;
import javax.swing.JFileChooser;
import static javax.swing.SwingUtilities.isRightMouseButton;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Screen extends javax.swing.JFrame implements KeyListener, MouseListener {

    private final ArrayList<Element> elements;
    private final ArrayList<ArrayList<ArrayList<Element>>> elementsMatrix;
    private final ArrayList<Element> removedElements;
    private final GameControl control = new GameControl();
    private Graphics g2;
    private final Stage stage;
    private String background;

    public Screen() throws IOException {
        initComponents();
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
    public synchronized void startGame() {
        Draw.setScene(this);
        this.addKeyListener(this);
        this.addMouseListener(this);
        this.setBackgroundImage("blank.png");
        Stage.start(this);
    }

    public synchronized void startFirstStage() {
        this.setBackgroundImage("background.png");
        this.clearStage();
        
        try {
            stage.setStage(1);
            stage.print(this);
            if (!SaveAndLoad.getInstance().isAlive()) {
                SaveAndLoad.getInstance().start();
            }
        } catch (FileNotFoundException f) {
            System.out.println(f.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public Stage getStage() {
        return this.stage;
    }
    
    public String getNumStage() {
        return String.valueOf(this.stage.getStage());
    }
    
    public void setNumStage(int numStage) {
        this.stage.setStage(numStage);
    }

    public synchronized void addElement(Element anElement) {
        elements.add(anElement);
        elementsMatrix.get(anElement.getPosition().getCol())
                .get(anElement.getPosition().getRow())
                .add(anElement);
        elements.sort(new ElementComparator());
    }

    /*
    Remove os elementos de fato. Chamado no paint.
     */
    public synchronized void trueRemoveElemento(Element anElement) {
        elements.remove(anElement);
        elementsMatrix.get(anElement.getPosition().getCol())
                .get(anElement.getPosition().getRow())
                .remove(anElement);
    }

    /*
    Método que adiciona um elemento para remoção. A remoção em si será feita
    no paint.
     */
    public synchronized void removeElement(Element anElement) {
        removedElements.add(anElement);
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
        if (p.getCol() >= Consts.RES & p.getRow() >= Consts.RES) {
            return null;
        }
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
    @SuppressWarnings("unchecked")
    public synchronized void paint(Graphics gOld) {
        SaveAndLoad.getInstance().setActive(false);
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
        SaveAndLoad.getInstance().setActive(true);
    }

    /*
    Função que limpa a tela, ie, remove todos os elementos do vetor elements.
     */
    @SuppressWarnings("unchecked")
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
    public synchronized void nextStage() {
        this.clearStage();
        try {
            stage.next();
            stage.print(this);
        } catch (IOException ex) {
            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public synchronized void resetStage() {
        this.clearStage();
        try {
            stage.print(this);
        } catch (IOException ex) {
            stage.start(this);
        }
    }

    /*
    Reinicia o jogo (ocorrerá quando o número de vidas do Bomberman zerar).
     */
    public synchronized void resetGame() {
        this.clearStage();
        this.setBackgroundImage("blank.png");
        stage.start(this);
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
    @SuppressWarnings("unchecked")
    public void keyPressed(KeyEvent e) {
        for (Element el : (ArrayList<Element>) elements.clone()) {
            el.Event(e.getKeyCode(), control, this);
        }
    }
    
    public void mousePressed(MouseEvent e) {
        if (!isRightMouseButton(e)) {
            return;
        }
        int x = e.getX()/Consts.CELL_SIDE;
        int y = e.getY()/Consts.CELL_SIDE;
        ArrayList<Element> elementsInPosition = this.searchElement(new Position(x, y));
        if (!elementsInPosition.isEmpty()) {
            try {
                this.swapElement(elementsInPosition.get(0));
            } catch (IOException ex) {
                Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private void swapElement(Element e) throws FileNotFoundException, IOException, ClassNotFoundException {
        JFileChooser chooser = new JFileChooser();
        File workingDirectory = new File(System.getProperty("user.dir"));
        chooser.setCurrentDirectory(workingDirectory);
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Serialized elements", "dat");
        chooser.setFileFilter(filter);
        int returnVal = chooser.showOpenDialog(this);
        if(returnVal == JFileChooser.APPROVE_OPTION) {
            FileInputStream file = new FileInputStream(chooser.getSelectedFile());
            ObjectInputStream in = new ObjectInputStream(file);
            Element newElement = (Element) in.readObject();
            Position oldPosition = e.getPosition();
            e.remove();
            this.addElement(newElement);
            newElement.setPosition(oldPosition.getCol(), oldPosition.getRow());
        }
        else {
            return;
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
        SaveAndLoad.getInstance().terminate();
        this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent me) {
    }

    @Override
    public void mouseReleased(MouseEvent me) {
    }

    @Override
    public void mouseEntered(MouseEvent me) {
    }

    @Override
    public void mouseExited(MouseEvent me) {
    }

}
