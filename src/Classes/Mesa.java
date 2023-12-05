package Classes;

import java.util.ArrayList;

public class Mesa {
    private Pedido pedido;
    private int numMesa;
    private int capacidade;
    private boolean ocupado;
    private ArrayList<Cliente> clientes;

    public Mesa(int numMesa, int capacidade) {
        this.numMesa = numMesa;
        this.capacidade = capacidade;
        this.clientes = new ArrayList<>();
        this.pedido = new Pedido();
    }
    
    public Pedido getPedido() {
        return pedido;
    }
    
    public void addPedido(Item item, int quant){
        pedido.setItens(item);
        pedido.setQuantidades(quant);
    }

    public ArrayList<Cliente> getClientes() {
        return clientes;
    }

    public void addClientes(Cliente cliente) {
        this.clientes.add(cliente);
    }    
    
    public int getNumMesa() {
        return numMesa;
    }

    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public void setCapacidade(int capacidade) {
        this.capacidade = capacidade;
    }

    public boolean isOcupado() {
        return ocupado;
    }

    public void setOcupado(boolean ocupado) {
        this.ocupado = ocupado;
    }
}

