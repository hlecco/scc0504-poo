package ex3;

public class Ex3 {

    public static void main(String[] args) {
        ArvoreBinaria<Integer> ab = new ArvoreBinaria();
        ab.insere(4);
        ab.insere(5);
        ab.insere(2);
        ab.insere(3);
        ab.insere(1);
        System.out.println("Primeiro:");
        ab.imprime();
        ab.deleta(2);
        System.out.println("Segundo:");
        ab.imprime();
        ab.deleta(6);
        System.out.println("Terceiro:");
        ab.imprime();
    }

}
