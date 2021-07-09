package ex7;

public abstract class DividaDecorator extends Divida {
    
    Divida d;
    
    DividaDecorator(Divida d) {
        this.d = d;
    }
    
}
