package Classes;

import java.util.ArrayList;

public class Cardapio {
    private ArrayList<Item> itens;

    public Cardapio() {
        this.itens = new ArrayList<>();
    }

    public ArrayList<Item> getItens() {
        return itens;
    }
    public void addItem(String nome, float preco){
        Item item = new Item(nome,preco);
        itens.add(item);
    }
}
