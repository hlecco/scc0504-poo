/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import Auxiliar.Consts;
import java.io.Serializable;

public class Transicao extends Elemento implements Serializable {
    public Transicao(String filename) {
        super(filename, Consts.RES, Consts.RES, 1, 0, 0);
        this.addClock(15, 1, null, this::remove, false);
        this.priority = 20;
    }
}
