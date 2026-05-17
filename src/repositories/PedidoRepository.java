package repositories;

import entities.Pedido;

public interface PedidoRepository {
    public void salvarPedido(Pedido pedido);
    public void salvarLog(String msg);
}
