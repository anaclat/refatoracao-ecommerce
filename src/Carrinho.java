import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class Carrinho {
    private HashMap<Produto, Integer> produtos = new HashMap<>();

    public Carrinho(HashMap<Produto, Integer> produtos) {
        this.produtos = produtos;
    }

    public void adicionarItem(Produto produto, int qtd) {

        boolean itemExiste = checaItemExiste(produto);

        if (itemExiste) {
            produtos.put(produto, produtos.get(produto) + qtd);
            return;
        }

        produtos.put(produto, qtd);
    }

    public int obterQuantidadeProduto(Produto produto) {
        return produtos.get(produto);
    }

//    public double obterPrecoProduto(Produto produto){
//        return  produtos.get(produto).
//    }

    private boolean checaItemExiste(Produto produto) {
        return this.produtos.containsKey(produto);
    }

    public Set<Produto> getProdutos() {
        return produtos.keySet();
    }

}
