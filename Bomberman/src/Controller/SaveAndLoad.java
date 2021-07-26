package Controller;

import Auxiliar.Consts;
import Auxiliar.Draw;
import Model.Element;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
    private boolean active;

    private SaveAndLoad() {
        this.timer = Consts.TIMER;
        this.exit = false;
        this.active = false;
    }

    @Override
    public void run() {
        try {
            this.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(SaveAndLoad.class.getName()).log(Level.SEVERE, null, ex);
        }
        while (!exit) {
            this.save();
            try {
                this.sleep(this.timer);
            } catch (InterruptedException ex) {
                Logger.getLogger(SaveAndLoad.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void setActive(Boolean state) {
        this.active = state;
    }

    public void terminate() {
        exit = true;
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

    public synchronized void save() {
        if (!this.active) {
            return;
        }
        File zipfile = new File("load/saved.zip");
        @SuppressWarnings("unchecked")
        ArrayList<Element> copyElements = (ArrayList<Element>) Draw.getScreen().getElements().clone();
        try {
            ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(zipfile));
            zos.putNextEntry(new ZipEntry("metadata.txt"));
            String temp = Draw.getScreen().getNumStage() + "\n" + String.valueOf(copyElements.size()) + "\n";
            zos.write(temp.getBytes(), 0, temp.length());
            
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

    public synchronized void load() {
        Screen screen = Draw.getScreen();
        screen.clearStage();
        screen.setBackgroundImage("background.png");
        try {
            ZipFile zf = new ZipFile("load/saved.zip");
            ZipEntry entrytxt = zf.getEntry("metadata.txt");
            ZipEntry entrydat = zf.getEntry("objects.dat");
            InputStream inputtxt = zf.getInputStream(entrytxt);
            InputStream inputdat = zf.getInputStream(entrydat);
            BufferedReader buffertxt = new BufferedReader(new InputStreamReader(inputtxt));
            ObjectInputStream deserializer = new ObjectInputStream(inputdat);
            String line;
            Element obj = null;
            int numberOfElements = 0;
            if ((line = buffertxt.readLine()) != null) {
                Draw.getScreen().getStage().setStage(Integer.parseInt(line));
            }
            if ((line = buffertxt.readLine()) != null) {
                numberOfElements = Integer.parseInt(line);
            }
            for (int i=0; i<numberOfElements; i++) {
                try {
                    obj = (Element) deserializer.readObject();
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(SaveAndLoad.class.getName()).log(Level.SEVERE, null, ex);
                }
                if (obj != null) {
                    screen.addElement(obj);
                }
            }
            exit = false;
            this.active = true;
        } catch (IOException ex) {
            Logger.getLogger(Screen.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
