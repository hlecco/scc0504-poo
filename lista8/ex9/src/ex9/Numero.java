package ex9;

public abstract class Numero {
    
    final Numero proximo;
    
    public Numero(Numero proximo) {
        this.proximo = proximo;
    }
    
    public abstract void raiz(JogaNumero jn);
    public abstract void quadrado(JogaNumero jn);
    
}
