package ex3a;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Ex3a {

    public static void main(String[] args) {
        /*
        No final o saldo deveria ser igual ao total de dinheiro depositado menos
        a quantidade de dinheiro sacada, mas, por conta da concorrência, isso
        nem sempre ocorrerá, como pode ser conferido ao rodar o programa algumas
        vezes. O problema é o usuário e o banco alterarem o saldo ao mesmo tempo.
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

        int saldoFinal = cb.getSaldoFinal();
        int totalDepositado = cb.getTotalDepositado();
        int totalSacado = cb.getTotalSacado();
        
        if (totalDepositado - totalSacado == saldoFinal) {
            System.out.println("Funcionou.");
        } else {
            System.out.println("Não funcionou.");
        }
        
    }

}
