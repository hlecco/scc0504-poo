package ex3b;

import java.util.logging.Level;
import java.util.logging.Logger;

public class Ex3b {

    public static void main(String[] args) {
        /*
        Ao final o saldo deveria ser 0, pois o usuário adiciona a mesma qtde
        de dinheiro (12,497,500) que o banco saca, mas na verdade o synchronized
        não necessariamente resolve o problema, pois, além de o depósito e do
        saque poderem alterar o saldo ao mesmo tempo, pode ocorrer também de
        a thread do banco começar antes da thread do usuário, o que faz com que
        o programa também não funcione corretamente. Uma possível solução para
        tal problema é forçar a thread do banco (ie, bt) a começar depois da
        thread do usuário (ut). Para isso basta colocar o bt.start() após o
        ut.join(). No entanto, fazendo o mesmo procedimento no item a também
        resolveu o problema (talvez seja apenas coincidência, já que o problema
        de ambas as threads alterarem o saldo ao mesmo tempo ainda deveria
        ocorrer) e isso me leva à solução que me parece a mais adequada, que é
        usar wait e notify. Trocando o código run do bancoThread e do
        usuarioThread para sacaCorreto e depositaCorreto, respectivamente, o
        código roda como esperado, ie, o saldo dará sempre 0 no final. Usando
        apenas synchronized, na maior parte dos casos o saldo dá 0, pois o
        synchronized evita que o banco e o usuário alterem o saldo ao mesmo
        tempo, mas não resolve o problema de o banco começar a sacar antes do
        usuário depositar.
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
        
        cb.getSaldo();
    }

}
