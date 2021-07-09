package ex10;

import java.util.Observable;
import java.util.Observer;

public class ObservadorTamanho implements Observer {

    @Override
    public void update(Observable o, Object o1) {
        Observavel temp = (Observavel) o;
        String texto = temp.getTexto();
        texto += texto.length();
        System.out.println(texto);
    }
    
}
