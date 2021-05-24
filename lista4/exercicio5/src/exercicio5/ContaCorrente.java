package exercicio5;


public class ContaCorrente {
    private float saldo;
    private float creditoLimite;
    
    /*
    Troquei o método setValorLimite do enunciado por um construtor,
    já que tal método não iria lançar exceções
    */
    ContaCorrente(float pSaldo, float pCreditoLimite) {
        saldo = pSaldo;
        creditoLimite = pCreditoLimite;
    }
    
    public void sacar(float pValor) {
        if (pValor > saldo + creditoLimite) {
            throw new ContaExcecao("Saldo abaixo do valor desejado.");
        } else {
            saldo -= pValor;
        }
    }
    
    public void depositar(float pValor) {
        if (pValor <= 0) {
            throw new ContaExcecao("Nao foi possivel realizar deposito. "
            + "Valor informado eh invalido.");
        } else {
            saldo += pValor;
        }
    }
    
    public void getSaldo() {
        System.out.println("O saldo atual eh: " + saldo);
    }
}
