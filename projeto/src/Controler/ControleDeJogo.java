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
}