package ex7;

public class JurosDescTaxa extends DividaDecorator {
    
    JurosDescTaxa(Divida d) {
        super(d);
    }
    
    @Override
    public int getValor() {
        return d.getValor() + 7;
    }
    
}
