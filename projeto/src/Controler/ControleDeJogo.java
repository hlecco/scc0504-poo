package Controler;

import java.util.ArrayList;

import Modelo.Elemento;
import Modelo.Hero;
import Auxiliar.Posicao;
import Modelo.BomberUp;
import Modelo.FireUp;
import Modelo.Fogo;


public class ControleDeJogo {
    
    public void desenhaTudo(ArrayList<Elemento> e) {
        for (Elemento elem : e) {
            elem.autoDesenho();
        }
    }
    
    public void processaTudo(ArrayList<Elemento> e) {
        
    }
    
    public int ehPosicaoValida(ArrayList<Elemento> e, Posicao p) {
        // Validacao da posicao de todos os elementos com relacao a Posicao p
        for (Elemento eTemp : e) { // Olha todos os elementos
            if (eTemp.getPosicao().estaNaMesmaPosicao(p)) {
                if (eTemp.isbDestrutivel())
                    return 2; // Tem um elemento destrutível na posição
                if (!eTemp.isbTransponivel())
                    return 0; // A posicao p é invalida, pois ha um elemento (i-esimo eTemp) intransponivel lá
                if (eTemp.isbTransponivel()) {
                    if (eTemp.getHiddenItem() == 1) {
                        return 3; // Tem um bomberup na posição
                    }
                    if (eTemp.getHiddenItem() == 2) {
                        return 4; // Tem um fireup na posição
                    }
                    if (eTemp.getHiddenItem() == 3) {
                        return 5; // Tem porta na posição
                    }
                    if (eTemp.getHiddenItem() == 4) {
                        return 6; // Tem fogo na posição
                    }
                }
            }
        }
        return 1;
    }
}