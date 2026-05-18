package entities;

import java.util.HashMap;

public class Carrinho {
    private HashMap<Produto, Integer> produtos;

    public Carrinho() {
        produtos = new HashMap<>();
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

//    public double obterPrecoProduto(model.Produto produto){
//        return  produtos.get(produto).
//    }

    private boolean checaItemExiste(Produto produto) {
        return this.produtos.containsKey(produto);
    }

    public HashMap<Produto, Integer> getProdutos() {
        // Retorna uma copia do hashmap de produtos para garantir que a estrutura interna nao seja modificada
        return (HashMap<Produto, Integer>) produtos.clone();
    }

}
