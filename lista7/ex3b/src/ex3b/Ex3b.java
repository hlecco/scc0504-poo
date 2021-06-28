package ex3b;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Ex3b {

    public static void main(String[] args) {
        /*
        No final o saldo deveria ser igual ao total de dinheiro depositado menos
        a quantidade de dinheiro sacada, mas, por conta da concorrência, isso
        nem sempre ocorrerá, como pode ser conferido ao rodar o programa algumas
        vezes. O problema é o usuário e o banco alterarem o saldo ao mesmo tempo,
        mas nesse caso isso não ocorre. No entanto, pode acontecer de a thread
        do banco começar antes do usuário, o que faz com que o saldo no final
        nem sempre seja 0, mas a fórmula inicial de saldo = depositado - sacado
        sempre funciona.
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
            Logger.getLogger(Ex3b.class.getName()).log(Level.SEVERE, null, ex);
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
