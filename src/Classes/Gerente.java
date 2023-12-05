package Classes;
import java.util.ArrayList;

public class Gerente{
    private String nome;
    private String cpf;
    private String rg;
    private String senha;
    private ArrayList<Mesa> mesas;
    private ArrayList<Funcionario> funcionarios;
    private Cardapio cardapio;
    
    public Gerente(String rg, String senha, String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
        this.rg = rg;
        this.senha = senha;
        this.funcionarios = new ArrayList<>(); // Inicializa a lista de funcionários
        this.mesas = new ArrayList<>(); // Inicializa mesas
        this.cardapio = new Cardapio(); // Inicializa o cardápio
    }

    public Gerente() {
    }

    public Cardapio getCardapio() {
        return cardapio;
    }

    public ArrayList<Mesa> getMesas() {
        return mesas;
    }

    public void adicionarMesas(Mesa mesa) {
        mesas.add(mesa);
    }
    
    public void removerMesa(Mesa mesa){
        mesas.remove(mesa);
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public void adicionarFuncionario(String nome, String cpf, String data, String senha) {
        Funcionario func = new Funcionario(nome, cpf, data, senha);
        funcionarios.add(func);
    }
    public void removerFuncionario(Funcionario func){
        funcionarios.remove(func);
    }
    public void editarFuncionario(Funcionario func, String nome, String cpf, String data, String senha){
        int index = funcionarios.indexOf(func);
        Funcionario func2 = new Funcionario(nome, cpf, data, senha);
        funcionarios.remove(index);
        funcionarios.add(index, func2);
    }
    
    public ArrayList<Funcionario> getFuncionarios() {
        return funcionarios;
    }
    
    public void alocarMesa(Funcionario funcionario, Mesa mesa){
        funcionario.addMesa(mesa);
    }
    
    public void adicionarItem (String nome, float preco){
        cardapio.addItem(nome,preco);
    }
}
