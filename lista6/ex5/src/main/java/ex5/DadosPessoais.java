package ex5;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class DadosPessoais implements Serializable {
    private Endereco endereco;
    private String nome;
    private CPF cpf;
    
    DadosPessoais(String e, String n, String c) {
        endereco = new Endereco(e);
        nome = n;
        cpf = new CPF();
        cpf.set(c);
    }
    
    public void setNome(String n) {
        nome = n;
    }
    public void setEndereco(Endereco e) {
        endereco = e;
    }
    public void setEndereco(String e) {
        endereco = new Endereco(e);
    }
    public void setCPF(CPF c) {
        cpf = c;
    }
    public void setCPF(String c) {
        cpf.set(c);
    }
    
    public void show() {
        System.out.println("Nome: " + nome);
        System.out.println("Endereço " + endereco.get());
        System.out.println("CPF: " + cpf.get());
    }
    
    public static DadosPessoais read_file(String path) throws FileNotFoundException, IOException, ClassNotFoundException {
        File input_file = new File(path);
        FileInputStream input_stream = new FileInputStream(input_file);
        GZIPInputStream input_gz = new GZIPInputStream(input_stream);
        ObjectInputStream input_obj = new ObjectInputStream(input_gz);
        return (DadosPessoais) input_obj.readObject();
    }
    
    public void write_file(String path) throws FileNotFoundException, IOException {
        File output_file = new File(path);
        FileOutputStream output_stream = new FileOutputStream(output_file);
        GZIPOutputStream output_gz = new GZIPOutputStream(output_stream);
        ObjectOutputStream output_obj = new ObjectOutputStream(output_gz);
        output_obj.writeObject(this);
        output_obj.close();
    }
}
