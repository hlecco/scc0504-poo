package Model;

import java.util.Comparator;

/*
Classe que faz com que os elementos sejam desenhados segundo suas prioridades.
Ou seja, se a prioridade de A for maior que a de B, A é desenhado por cima de B,
como no caso do Bomberman e da bomba.
*/
public class ElementComparator implements Comparator<Element> {

    @Override
    public int compare(Element e1, Element e2) {
        Integer i1 = e1.getPriority();
        Integer i2 = e2.getPriority();
        return i1.compareTo(i2);
    }

}
