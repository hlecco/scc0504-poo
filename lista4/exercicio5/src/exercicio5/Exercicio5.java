package exercicio5;


public class Exercicio5 {
    
    public static void main(String[] args) {
        ContaCorrente cc = new ContaCorrente(100, 200);
        
        try { // Depósito ok
            cc.depositar(100);
            cc.getSaldo();
        } catch (ContaExcecao e) {
            System.out.println(e.getMessage());
        }
        
        try { // Depósito inválido
            cc.depositar(-1);
            cc.getSaldo();
        } catch (ContaExcecao e) {
            System.out.println(e.getMessage());
        }
        
        try { // Saque ok
            cc.sacar(100);
            cc.getSaldo();
        } catch (ContaExcecao e) {
            System.out.println(e.getMessage());
        }
        
        try { // Saque invalido
            cc.sacar(500);
            cc.getSaldo();
        } catch (ContaExcecao e) {
            System.out.println(e.getMessage());
        }
    }
    
}
