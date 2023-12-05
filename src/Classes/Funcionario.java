package Classes;
import java.util.ArrayList;

public class Funcionario extends Pessoa{
    private String senha;
    private String data;
    private ArrayList<Mesa> mesas;
    
    public Funcionario(String nome, String cpf, String data, String senha) {
        super(nome, cpf);
        this.senha = senha;
        this.data = data;
        this.mesas = new ArrayList<>();
    }

    public ArrayList<Mesa> getMesas() {
        return mesas;
    }

    public void addMesa(Mesa mesa) {
        this.mesas.add(mesa);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
    
    public void addCliente(Cliente cliente, Mesa mesa){
        mesa.addClientes(cliente);
    }
}
