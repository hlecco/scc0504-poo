package ex3a;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Ex3a {

    public static void main(String[] args) {
        /*
        Ao final o saldo deveria ser 0, pois o usuário adiciona a mesma qtde
        de dinheiro (12,497,500) que o banco saca, mas na maior parte
        dos casos o saldo fica positivo e aparece a mensagem de saldo 
        insuficiente, que significa que o banco tentou retirar mais dinheiro
        do que havia na conta.
         */
        ContaBancaria cb = new ContaBancaria();
        Thread ut = new Thread(new usuarioThread(cb));
        Thread bt = new Thread(new bancoThread(cb));

        ut.start();
        bt.start();

        try {
            ut.join();
            bt.join();
        } catch (InterruptedException ex) {
            Logger.getLogger(Ex3a.class.getName()).log(Level.SEVERE, null, ex);
        }

        cb.getSaldo();

    }

}
