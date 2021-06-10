package ex5;

import java.io.Serializable;

public class CPF implements Serializable {
    private String cpf = "00000000000";
    
    private boolean check() {
        int verificador_1 = 0;
        int verificador_2 = 0;
        for (int i=0; i<9; i++) {
            verificador_1 += (10-i)*Character.getNumericValue(cpf.charAt(i));
            verificador_2 += (11-i)*Character.getNumericValue(cpf.charAt(i));
        }
        
        verificador_1 = 11 - (verificador_1 % 11);
        if (verificador_1 > 9) {
            verificador_1 = 0;
        }
        
        verificador_2 += verificador_1*2;
        verificador_2 = 11 - (verificador_2 % 11);
        if (verificador_2 > 9) {
            verificador_2 = 0;
        }
        
        return ((verificador_1 == Character.getNumericValue(cpf.charAt(9)))
                && (verificador_2 == Character.getNumericValue(cpf.charAt(10)))
                );
    }
    
    public void set(String c) {
        if (c.matches("[0-9]{11}")) {
            cpf = c;
            if (!this.check()) {
                throw new IllegalArgumentException("Falha na verificação");
            }
        }
        else {
            throw new IllegalArgumentException("CPF deve conter apenas números");
        }
        
    }
    
    public String get() {
        return cpf.substring(0, 3)
               + "."
               + cpf.substring(3, 6)
               + "."
               + cpf.substring(6, 9)
               + "-"
               + cpf.substring(9, 11);
    }
}
