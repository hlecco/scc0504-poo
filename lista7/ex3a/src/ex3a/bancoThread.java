package ex3a;

public class bancoThread implements Runnable {

    private final ContaBancaria cb;

    bancoThread(ContaBancaria cb) {
        this.cb = cb;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5000; i++) {
            cb.saca(i);
        }
    }

}
