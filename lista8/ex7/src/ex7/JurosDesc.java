package ex7;

public class JurosDesc extends DividaDecorator {
    
    JurosDesc(Divida d) {
        super(d);
    }
    
    @Override
    public int getValor() {
        return d.getValor() + 5;
    }
    
}
