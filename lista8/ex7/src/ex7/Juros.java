package ex7;

public class Juros extends DividaDecorator {
    
    Juros(Divida d) {
        super(d);
    }
    
    @Override
    public int getValor() {
       return d.getValor() + 10;
    }
    
}
