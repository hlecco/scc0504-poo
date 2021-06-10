package ex5;

import java.io.Serializable;

public class Endereco implements Serializable {
    private String rua;
    private String numero;
    private String cidade;
    private String estado;
    
    Endereco(String full_address) {
        String[] fields = full_address.split(",", 4);
        try {
            setRua(fields[0].trim());
            setNumero(fields[1].trim());
            setCidade(fields[2].trim());
            setEstado(fields[3].trim());
        } catch(ArrayIndexOutOfBoundsException e) {
            rua = "";
            numero = "";
            cidade = "";
            estado = "";
        }
    }
    
    public void setRua(String r) {
        rua = r;
    }
    public void setNumero(String n) {
        numero = n;
    }
    public void setCidade(String c) {
        cidade = c;
    }
    public void setEstado(String e) {
        estado = e;
    }
    
    public String get() {
        return rua + ", "
               + numero + ", "
               + cidade + ", "
               + estado;
    }
}
