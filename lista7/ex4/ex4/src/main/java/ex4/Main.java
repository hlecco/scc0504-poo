/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ex4;

import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lecco
 */
public class Main {
    public static void main(String[] args) {
        // TODO code application logic here
        long startTime = 0;
        try {
            /*Carga: computar a raiz quadrada de 1.000.000.000 números aleatórios*/
            if (args.length != 3) {
                System.out.println("SYNTAX: nOperations Operation Multithreaded(m or s)");
                return;
            }
            int iTotalDeCalculos = Integer.parseInt(args[0]);
            boolean multiThreaded = false;
            if (args[2].charAt(0) == 'm') {
                multiThreaded = true;
            }
            char operation = args[1].charAt(0);
            
            int nCores;
            if (multiThreaded == true) {
                nCores = Runtime.getRuntime().availableProcessors();
            }
            else {
                nCores = 1;
            }
            
            ArrayList<Double> randList = new ArrayList<Double>(iTotalDeCalculos);
            var rand = new Random();
            for (int i=0; i<iTotalDeCalculos; i++) {
                randList.add(rand.nextDouble());
            }
            
            //nCores = 1;
            Calculator[] cs = new Calculator[nCores];
            startTime = System.currentTimeMillis();
            for (int i = 0; i < nCores; i++) {
                /*Cada core recebe uma fatia igual da carga*/
                cs[i] = new Calculator(randList.subList(i*(iTotalDeCalculos/nCores),
                                       (i+1)*(iTotalDeCalculos/nCores)), operation);
                cs[i].start();
            }

            for (int i = 0; i < nCores; i++) {
                cs[i].join();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        long estimatedTime = System.currentTimeMillis() - startTime;
        System.out.println(estimatedTime + "ms");
    }
}
