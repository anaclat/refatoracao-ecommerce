package repositories;

import entities.Pedido;

public interface PedidoRepository {
    public static void salvarPedido(Pedido pedido) {};
    public static void salvarLog(String msg) {};
}
