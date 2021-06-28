package ex3b;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ContaBancaria {

    private int saldo;
    private int totalDepositado;
    private int totalSacado;

    synchronized public void deposita(int valor) {
        saldo += valor;
        totalDepositado += valor;
    }

    synchronized public int saca(int valor) {
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
