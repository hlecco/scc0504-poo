package ex7;

public class Ex7 {

    public static void main(String[] args) {
        
        Divida d = new Divida(50);
        System.out.println(d.getValor());
        
        Juros j = new Juros(d);
        System.out.println(j.getValor());
        JurosDesc jd = new JurosDesc(d);
        System.out.println(jd.getValor());
        JurosDescTaxa jdt = new JurosDescTaxa(d);
        System.out.println(jdt.getValor());
        
    }
    
}
