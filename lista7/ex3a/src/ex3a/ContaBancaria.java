package ex3a;

public class ContaBancaria {

    private int saldo;
    private int totalDepositado;
    private int totalSacado;

    public void deposita(int valor) {
        int temp = saldo + valor;
        saldo = temp;
        totalDepositado += valor;
    }

    public int saca(int valor) {
        if (valor <= saldo) {
            int temp = saldo - valor;
            saldo = temp;
            totalSacado += valor;
            return valor;
        } else {
            return -1;
        }
    }

    public int getSaldoFinal() {
        return saldo;
    }

    public int getTotalSacado() {
        return totalSacado;
    }

    public int getTotalDepositado() {
        return totalDepositado;
    }

}
