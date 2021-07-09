package Sistema;


public class Main {
    public static void main(String[] args) {
        String password = Constants.defaultPassword;
        
        
        // Sistema
        System.out.println("Using base class, without proxy or decorator");
        Sistema sistemaBase = new Sistema();
        System.out.println("Trying to set default password");
        sistemaBase.setPassword(password);
        System.out.println("Trying to connect with default password");
        if (sistemaBase.connect(password)) {
            System.out.println("Connection successful");
        }
        else {
            System.out.println("Connection failed");
        }
        
        
        // ProxySeguranca
        System.out.println("Using proxy");
        ProxySeguranca sistemaProxy = new ProxySeguranca();
        System.out.println("Trying to set default password");
        try {
            sistemaProxy.setPassword(password);
        } catch (Exception error) {
            error.printStackTrace();
        }
        System.out.println("Trying to connect with default password");
        
        try {
            if (sistemaProxy.connect(password)) {
                System.out.println("Connection successful");
            }
            else {
                System.out.println("Connection failed");
            }
        } catch (Exception error) {
            error.printStackTrace();
        }
        
        // FilteredSistema
        System.out.println("Using decorator");
        FilteredSistema sistemaDecorator = new FilteredSistema(sistemaBase);
        System.out.println("Trying to set default password");
        sistemaDecorator.setPassword(password);
        System.out.println("Trying to connect with default password");
        if (sistemaDecorator.connect(password)) {
            System.out.println("Connection successful");
        }
        else {
            System.out.println("Connection failed");
        }
    }
}
