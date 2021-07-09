package Sistema;


public class Sistema {
    private String password;  

    public Sistema() {
        this.password = Constants.defaultPassword;
    }
    
    public boolean connect(String pass) {
        if (pass == this.password) {
            if (this.password == Constants.defaultPassword) {
               System.out.println("Using the default password is not recommended");
            }
            return true;
        }
        return false;
    }
    
    public void setPassword(String pass) {
        if (pass == Constants.defaultPassword) {
            System.out.println("Using the default password is not recommended");
        }
        this.password = pass;
    }
}
