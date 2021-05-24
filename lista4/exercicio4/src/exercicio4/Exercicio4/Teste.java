package exercicio4.Exercicio4;


public class Teste {

    public static void main(String[] args) {
        Pilha p1 = new Pilha(3);
        Pilha p2 = new Pilha(3);
        Pilha p3 = new Pilha(1);
        
        System.out.println("Pilha 1:");
        try { // Funciona
            p1.push("Caio");
            p1.push("Lecco");
            p1.push("Rodolfo");
        
            System.out.println(p1.pop());
        
            p1.push("Joao");
        
            for (int i = 0; i < 3; i++) {
                System.out.println(p1.pop());
            }
        } catch (PilhaExcecao e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println("\nPilha 2:");
        try { // "Não" funciona, dá mensagem de pilha cheia
            p2.push("Caio");
            p2.push("Lecco");
            p2.push("Rodolfo");
            p2.push("Oflodor");
        } catch (PilhaExcecao e) {
            System.out.println(e.getMessage());
        }
        
        System.out.println("\nPilha 3:");
        try { // Não funciona, dá mensagem de pilha vazia
            p3.push("Rodolfo");
            System.out.println(p3.pop());
            p3.pop();
        } catch (PilhaCheia e) { 
            System.out.println(e.getMessage());
        } catch (PilhaVazia e) {
            System.out.println(e.getMessage());
        } catch (Exception e) {
            System.out.println("Erro desconhecido.");
        } finally {
            System.out.println("\nFim do programa.");
        }
    }   
}