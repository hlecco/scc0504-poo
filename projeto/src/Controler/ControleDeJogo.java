package Controler;

import java.util.ArrayList;

import Modelo.Elemento;
import Modelo.Hero;
import Auxiliar.Posicao;


public class ControleDeJogo {
    
    public void desenhaTudo(ArrayList<Elemento> e) {
        for (int i = 0; i < e.size(); i++) {
            e.get(i).autoDesenho();
        }
    }
    
    public void processaTudo(ArrayList<Elemento> e) {
        
    }
    
    public int ehPosicaoValida(ArrayList<Elemento> e, Posicao p) {
        Elemento eTemp;
        // Validacao da posicao de todos os elementos com relacao a Posicao p
        for (int i = 1; i < e.size(); i++) { // Olha todos os elementos
            eTemp = e.get(i); // Pega o i-esimo elemento do jogo
            if (eTemp.isbDestrutivel() && eTemp.getPosicao().estaNaMesmaPosicao(p))
                return 2; // Tem um elemento destrutível na posição
            if (!eTemp.isbTransponivel() && eTemp.getPosicao().estaNaMesmaPosicao(p))
                return 0; // A posicao p é invalida, pois ha um elemento (i-esimo eTemp) intransponivel lá
        }
        return 1;
    }
}