package ex4;

import java.util.List;

/*
    r: raiz quadrada
    e: elevar a si mesmo
    c: cosseno
    s: soma 1
*/

public class Calculator extends Thread {
    private final List<Double> values;
    private char operation;

    public Calculator(List<Double> vl, char op) {
        this.values = vl;
        this.operation = op;
    }
 
    private double calculate(double x) {
        switch (this.operation) {
            case 'r':
                return Math.sqrt(x);
            case 'e':
                return Math.pow(x, x);
            case 'c':
                return Math.cos(x);
            case 's':
                return x+1;
        }
        return x;
    }
    
    @Override
    public void run() {
        for (Double x: values) {
            this.calculate(x);
        }
    }
}
