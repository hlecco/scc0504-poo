package ex9;

public class DivisivelDois extends Numero {

    DivisivelDois(Numero proximo) {
        super(proximo);
    }

    @Override
    public void raiz(JogaNumero jn) {
        int aux = jn.getNumero();

        if (aux % 2 == 0) {
            System.out.println("Quadrado: " + java.lang.Math.sqrt(aux));
        } else {
            proximo.raiz(jn);
        }
    }

    @Override
    public void quadrado(JogaNumero jn) {
        int aux = jn.getNumero();

        if (aux % 2 == 0) {
            System.out.println("Quadrado: " + aux * aux);
        } else {
            proximo.quadrado(jn);
        }
    }

}
