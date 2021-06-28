package ex3a;

public class ContaBancaria {

    private int saldo;

    public void deposita(int valor) {
        int temp = saldo + valor;
        saldo = temp;
    }

    public int saca(int valor) {
        if (valor <= saldo) {
            int temp = saldo - valor;
            saldo = temp;
            return valor;
        } else {
            System.out.println("Saldo insuficiente.");
            return 0;
        }
    }

    public void getSaldo() {
        System.out.println("Saldo: " + saldo);
    }

}
