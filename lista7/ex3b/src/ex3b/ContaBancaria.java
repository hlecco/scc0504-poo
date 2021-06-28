package ex3b;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ContaBancaria {

    private int saldo;

    synchronized public void deposita(int valor) {
        saldo += valor;
    }

    synchronized public int saca(int valor) {
        if (saldo < valor) {
            System.out.println("Saldo insuficiente.");
            return 0;
        } else {
            saldo -= valor;
            return valor;
        }
    }
    
    synchronized public void depositaCorreto(int valor) {
        saldo += valor;
        notify();
    }

    synchronized public int sacaCorreto(int valor) {
        while (saldo < valor) {
            try {
                wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(ContaBancaria.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        saldo -= valor;
        notify();
        return valor;
    }

    public void getSaldo() {
        System.out.println("Saldo: " + saldo);
    }

}
