package Model;

import java.util.Comparator;


public class ElementComparator implements Comparator<Element> {
    
    @Override
    public int compare(Element e1, Element e2) {
        Integer i1 = e1.getPriority();
        Integer i2 = e2.getPriority();
        return i1.compareTo(i2);
    }
    
}