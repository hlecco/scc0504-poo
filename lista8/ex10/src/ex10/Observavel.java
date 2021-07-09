package ex10;

import java.util.Observable;

public class Observavel extends Observable {
    
    private String texto;
    
    public void setTexto(String s) {
        texto = s;
        setChanged();
        notifyObservers();
    }
    
    public String getTexto() {
        return texto;
    }
    
}
