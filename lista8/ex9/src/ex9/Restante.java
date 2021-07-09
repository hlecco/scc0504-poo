package ex9;

public class Restante extends Numero {

    Restante(Numero proximo) {
        super(proximo);
    }

    @Override
    public void raiz(JogaNumero jn) {
        System.out.println("Quadrado: " + java.lang.Math.sqrt(jn.getNumero()));
    }

    @Override
    public void quadrado(JogaNumero jn) {
        System.out.println("Quadrado: " + jn.getNumero() * jn.getNumero());
    }

}
