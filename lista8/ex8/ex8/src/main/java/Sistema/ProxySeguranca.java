package Sistema;

public class ProxySeguranca {
    private Sistema sistema;
    
    public boolean connect(String pass) {
        if (this.sistema == null) {
            throw new IllegalStateException("Password has not been set");
        }
        else if (pass == Constants.defaultPassword) {
            throw new SecurityException("Using default password is not allowed");
        }
        return sistema.connect(pass);
    }
    
    public void setPassword(String pass) {
        if (pass == Constants.defaultPassword) {
            throw new SecurityException("Using default password is not allowed");
        }
        if (sistema == null) {
            this.sistema = new Sistema();
        }
        sistema.setPassword(pass);
    }
}
