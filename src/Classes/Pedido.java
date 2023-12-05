package Classes;
import java.util.ArrayList;

public class Pedido {
    private ArrayList<Integer> quantidades;
    private ArrayList<Item> itens;
    private float valor;

    public Pedido() {
        this.quantidades = new ArrayList<>();
        this.itens = new ArrayList<>();
        this.valor = 0;
    }

    public ArrayList<Integer> getQuantidades() {
        return quantidades;
    }

    public void setQuantidades(int quantidade) {
        this.quantidades.add(quantidade);
    }

    public ArrayList<Item> getItens() {
        return itens;
    }

    public void setItens(Item item ){
        this.itens.add(item);
    }

    public float getValor() {
        return valor;
    }   
}
