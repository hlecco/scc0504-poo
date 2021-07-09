package ex10;

import java.util.Calendar;
import java.util.Observable;
import java.util.Observer;

public class ObservadorAno implements Observer {

    @Override
    public void update(Observable o, Object o1) {
        Observavel temp = (Observavel) o;
        String texto = temp.getTexto();
        texto += Calendar.getInstance().get(Calendar.YEAR);
        System.out.println(texto);
    }
    
}
