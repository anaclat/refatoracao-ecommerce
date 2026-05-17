package repositories;

import entities.Pedido;

public class PedidoRepositoryBanco implements PedidoRepository {
    @Override
    public void salvarPedido(Pedido p) {
        System.out.println("Salvando pedido no banco...");
    }

    @Override
    public void salvarLog(String msg) {
        System.out.println("LOG: " + msg);
    }
}