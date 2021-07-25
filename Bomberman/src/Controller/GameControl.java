package Controller;

import java.util.ArrayList;

import Model.Element;


public class GameControl {
    
    public void drawAll(ArrayList<Element> Elements) {
        for (Element e : Elements) {
            e.step();
        }
    }
    
    public void processAll(ArrayList<Element> Elements) {
        for (Element e : (ArrayList<Element>) Elements.clone()) {
            e.selfDraw();
        }
    }
    
}