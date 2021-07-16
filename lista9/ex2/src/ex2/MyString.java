package ex2;

/*
Retorna o compareTo pelo tamanho da string no lugar da ordem lexicográfica.
 */
public class MyString implements Comparable {

    String s;
    int length;

    MyString(String s) {
        this.s = s;
        length = s.length();
    }

    @Override
    public String toString() {
        return s;
    }

    @Override
    public int compareTo(Object t) {
        MyString ms;
        ms = (MyString) t;
        return this.length - ms.length;
    }

}
