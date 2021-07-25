package Controller;

import Auxiliar.Consts;
import Auxiliar.Draw;
import Model.Bat;
import Model.Bomb;
import Model.Bomberman;
import Model.DestroyableWall;
import Model.Element;
import Model.Fire;
import Model.Life;
import Model.Radio;
import Model.Transition;
import Model.UndestroyableWall;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.NotSerializableException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

public class SaveAndLoad extends Thread {

    private static SaveAndLoad instance;
    private int timer;
    private boolean exit;
    private boolean running;

    private SaveAndLoad() {
        this.timer = Consts.TIMER;
        this.exit = false;
        this.running = false;
    }

    @Override
    public void run() {
        this.running = true;
        while (!exit) {
            this.save();
            try {
                Thread.sleep(this.timer);
            } catch (InterruptedException ex) {
                Logger.getLogger(SaveAndLoad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        this.running = false;
    }

    public void terminate() {
        exit = true;
        SaveAndLoad.instance = null;
    }

    public static SaveAndLoad getInstance() {
        if (SaveAndLoad.instance == null) {
            SaveAndLoad.instance = new SaveAndLoad();
        }
        return SaveAndLoad.instance;
    }

    public void setTimer(int pTimer) {
        this.timer = pTimer;
    }

    public void save() {
        File zipfile = new File("load/saved.zip");
        ArrayList<Element> copyElements = (ArrayList<Element>) Draw.getScreen().getElements().clone();
        try {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipfile));
            zos.putNextEntry(new ZipEntry("classes.txt"));
            for (Element e : copyElements) {
                String temp = e.getClass().toString() + "\n";
                zos.write(temp.getBytes(), 0, temp.length());
                temp = e.getStrSprite() + "\n";
                zos.write(temp.getBytes(), 0, temp.length());
            }
            zos.closeEntry();
            zos.putNextEntry(new ZipEntry("objects.dat"));
            ObjectOutputStream oos = new ObjectOutputStream(zos);
            for (Element e : copyElements) {
                oos.writeObject(e);
            }
            zos.closeEntry();
            oos.close();
            zos.close();
        } catch (IOException ex) {
            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void load() {
        Screen screen = Draw.getScreen();
        screen.clearStage();
        screen.setBackgroundImage("background.png");
        try {
            ZipFile zf = new ZipFile("load/saved.zip");
            ZipEntry entrytxt = zf.getEntry("classes.txt");
            ZipEntry entrydat = zf.getEntry("objects.dat");
            InputStream inputtxt = zf.getInputStream(entrytxt);
            InputStream inputdat = zf.getInputStream(entrydat);
            BufferedReader buffertxt = new BufferedReader(new InputStreamReader(inputtxt));
            ObjectInputStream deserializer = new ObjectInputStream(inputdat);
            String line;
            Element obj = null;
            Bomb bomb;
            Fire fire;
            Bat bat;
            String sprite;
            while ((line = buffertxt.readLine()) != null) {
                try {
                    obj = (Element) deserializer.readObject();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SaveAndLoad.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (line.contains("UndestroyableWall")) {
                    obj = (UndestroyableWall) obj;
                } else if (line.contains("DestroyableWall")) {
                    obj = (DestroyableWall) obj;
                } else if (line.contains("Radio")) {
                    obj = (Radio) obj;
                } else if (line.contains("Bomberman")) {
                    Bomberman temp = (Bomberman) obj;
                    Bomberman.load(temp);
                } else if (line.contains("Life")) {
                    obj = (Life) obj;
                } else if (line.contains("Bomb")) {
                    Bomb aux = (Bomb) obj;
                    bomb = new Bomb();
                    screen.addElement(bomb);
                    bomb.setUp();
                    bomb.setPotency(aux.getPotency());
                    bomb.setPosition(aux.getPosition().getCol(),
                            aux.getPosition().getRow());
                    buffertxt.readLine();
                    obj = null;
                } else if (line.contains("Bat")) {
                    Bat aux = (Bat) obj;
                    bat = new Bat();
                    screen.addElement(bat);
                    bat.setPosition(aux.getPosition().getCol(),
                            aux.getPosition().getRow());
                    buffertxt.readLine();
                    obj = null;
                } else if (line.contains("Fire")) {
                    obj = (Fire) obj;
                } else {
                    buffertxt.readLine();
                    continue;
                }
                if (obj != null) {
                    sprite = buffertxt.readLine();
                    String infos[] = sprite.split(" ");
                    obj.setSprite(infos[0], Integer.parseInt(infos[1]),
                            Integer.parseInt(infos[2]),
                            Integer.parseInt(infos[3]),
                            Integer.parseInt(infos[4]),
                            Integer.parseInt(infos[5]));
                    screen.addElement(obj);
                }
            }
            exit = false;
            this.start();
        } catch (IOException ex) {
            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
