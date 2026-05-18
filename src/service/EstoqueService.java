package service;

import entities.Pedido;
import entities.Produto;

import java.util.HashMap;
import java.util.Set;

public class EstoqueService {
    public static void atualizarEstoque(Pedido pedido) {
        HashMap<Produto, Integer> produtos = pedido.getProdutos();
        Set<Produto> produtosSet = produtos.keySet();

        for (Produto produto : produtosSet) {
            produto.decrementarQuantidade(produtos.get(produto));
        }
    }
}
