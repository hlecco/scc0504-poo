package ex3b;

public class usuarioThread implements Runnable {

    private final ContaBancaria cb;

    usuarioThread(ContaBancaria cb) {
        this.cb = cb;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            cb.deposita(i);
        }
    }

}
