package Modelo;

import java.util.Comparator;

public class ElementComparator implements Comparator<Elemento> {
    @Override
    public int compare(Elemento e1, Elemento e2) {
        Integer i1 = e1.getPriority();
        Integer i2 = e2.getPriority();
        return i1.compareTo(i2);
    }
}