package ex10;

import java.util.Observable;
import java.util.Observer;

public class ObservadorMaiuscula implements Observer {

    @Override
    public void update(Observable o, Object o1) {
        Observavel temp = (Observavel) o;
        String texto = temp.getTexto();
        texto = texto.toUpperCase();
        System.out.println(texto);
    }
    
}
