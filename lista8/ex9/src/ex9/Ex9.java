package ex9;

public class Ex9 {

    public static void main(String[] args) {
        Numero num = new DivisivelDois(new DivisivelTres(new Restante(null)));
        
        num.raiz(new JogaNumero(4));
        num.quadrado(new JogaNumero(4));
        num.raiz(new JogaNumero(9));
        num.quadrado(new JogaNumero(9));
        num.raiz(new JogaNumero(11));
        num.quadrado(new JogaNumero(11));
    }
    
}
