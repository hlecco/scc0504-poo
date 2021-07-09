package ex6a;

import java.util.ArrayList;

public class MeusVetores {
    
    ArrayList<Integer> conjuntoUm;
    ArrayList<Integer> conjuntoDois;
    
    MeusVetores(ArrayList<Integer> cUm, ArrayList<Integer> cDois) {
        conjuntoUm = cUm;
        conjuntoDois = cDois;
    }
    
    public ArrayList realizaOperacao(FuncoesMeusVetores fmv) {
        return fmv.Operacao(this);
    }
    
}
