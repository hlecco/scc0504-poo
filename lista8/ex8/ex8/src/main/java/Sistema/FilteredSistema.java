package Sistema;


public class FilteredSistema {
    private Sistema sistema;
    
    public FilteredSistema(Sistema sistemaBase) {
        this.sistema = sistemaBase;
    }
    
    public boolean connect(String pass) {
        if (pass == Constants.defaultPassword) {
            return this.sistema.connect(new StringBuilder(pass).reverse().toString());
        }
        else {
            return this.sistema.connect(pass);
        }
    }
    
    public void setPassword(String pass) {
        if (pass == Constants.defaultPassword) {
            this.sistema.setPassword(new StringBuilder(pass).reverse().toString());
        }
        else {
            this.sistema.setPassword(pass);
        }
    }
}
